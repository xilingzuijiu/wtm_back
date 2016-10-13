package com.weitaomi.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.weitaomi.application.model.bean.Article;
import com.weitaomi.application.model.bean.ArticleReadRecord;
import com.weitaomi.application.model.bean.OfficialAccount;
import com.weitaomi.application.model.bean.TaskPool;
import com.weitaomi.application.model.dto.ArticleDto;
import com.weitaomi.application.model.dto.ArticleReadRecordDto;
import com.weitaomi.application.model.dto.ArticleSearch;
import com.weitaomi.application.model.dto.ArticleShowDto;
import com.weitaomi.application.model.mapper.*;
import com.weitaomi.application.service.interf.IArticleService;
import com.weitaomi.application.service.interf.ICacheService;
import com.weitaomi.application.service.interf.IMemberScoreService;
import com.weitaomi.application.service.interf.IMemberTaskHistoryService;
import com.weitaomi.systemconfig.exception.BusinessException;
import com.weitaomi.systemconfig.exception.InfoException;
import com.weitaomi.systemconfig.util.*;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by supumall on 2016/7/7.
 */
@Service
public class ArticleService implements IArticleService {
    private Logger logger= LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleReadRecordMapper articleReadRecordMapper;
    @Autowired
    private IMemberScoreService memberScoreService;
    @Autowired
    private IMemberTaskHistoryService memberTaskHistoryService;
    @Autowired
    private ICacheService cacheService;
    @Autowired
    private TaskPoolMapper taskPoolMapper;
    @Autowired
    private OfficalAccountMapper officalAccountMapper;
    @Autowired
    private AccountAdsMapper accountAdsMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Page<ArticleShowDto> getAllArticle(Long memberId,ArticleSearch articleSearch) {
        if (articleSearch.getSearchWay()==0){
            Map<String,Long> idMap= memberMapper.getIsFollowWtmAccount(memberId);
            if (idMap!=null){
                if (idMap.get("officialMemberId")==null){
                    throw new InfoException("未关注微淘米服务号");
                }
            }
            List<ArticleShowDto> articleShowDtoList=articleMapper.getAtricleList(memberId,articleSearch,new RowBounds(articleSearch.getPageIndex(),articleSearch.getPageSize()));
            PageInfo<ArticleShowDto> showDtoPage=new PageInfo<ArticleShowDto>(articleShowDtoList);
            return Page.trans(showDtoPage);
        }else{
            //TODO
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean addArticle(ArticleDto article) {
        if (article==null){
            throw new BusinessException("文章信息为空");
        }
        if (article.getTitle()==null||!article.getTitle().isEmpty()){
            throw new BusinessException("文章标题为空");
        }
        if (article.getArticleAbstract()==null||article.getArticleAbstract().isEmpty()){
            throw new BusinessException("文章摘要为空");
        }
        if (article.getUrl()==null||!article.getUrl().isEmpty()){
            throw new BusinessException("文章地址为空");
        }
        if (article.getOfficialAccountId()==null){
            throw new BusinessException("文章所属机构为空");
        }
        article.setCreateTime(DateUtils.getUnixTimestamp());
        return articleMapper.insertSelective(article)>=0?true:false;
    }

    @Override
    @Transactional
    public Boolean modifyAticle(Article article) {
        if (article==null){
            throw new BusinessException("文章信息为空");
        }
        if (article.getTitle()==null||!article.getTitle().isEmpty()){
            throw new BusinessException("文章标题为空");
        }
        if (article.getArticleAbstract()==null||article.getArticleAbstract().isEmpty()){
            throw new BusinessException("文章摘要为空");
        }
        if (article.getUrl()==null||!article.getUrl().isEmpty()){
            throw new BusinessException("文章地址为空");
        }
        if (article.getOfficialAccountId()==null){
            throw new BusinessException("文章所属机构为空");
        }
        return articleMapper.updateByPrimaryKeySelective(article)>=0?true:false;
    }

    @Override
    public Boolean putArticleToTop(Long articleId, Integer isTop) {
        if (articleId==null){
            throw new BusinessException("所选文章ID为空");
        }
        return articleMapper.putArticleToTop(articleId, isTop)>=0?true:false;
    }

    @Override
    @Transactional
    public Boolean readArticle(Long memberId,String unionId,List<Long> articleId) {
        long ts=System.currentTimeMillis();
        if(memberId==null){
            throw new BusinessException("用户ID为空");
        }
        if(articleId.isEmpty()){
            throw new BusinessException("文章ID为空");
        }
        Long time=DateUtils.getUnixTimestamp();
        int number = articleReadRecordMapper.insertReadArticleRecord(memberId,articleId,0,time);
        boolean flag= number>0?true:false;
        if (flag){
            String url = PropertiesUtil.getValue("server.officialAccount.receiveAddRequest.url");
            String messageUrl = PropertiesUtil.getValue("server.officialAccount.message.url");
            Map<String,String>  map=new HashMap<>();
            map.put("unionId",unionId);
            map.put("url",messageUrl + "?memberId=" + memberId + "&requestTime=" +time);
            map.put("flag","0");
            Integer accountAdsId=accountAdsMapper.getLatestAccountAdsId();
            if (accountAdsId!=null){
                map.put("accountAdsId",accountAdsId.toString());
            }
            try {
                logger.info("时间为：{}",System.currentTimeMillis()-ts);
                HttpRequestUtils.postStringEntity(url,JSON.toJSONString(map));
                logger.info("交互时间为：{}",System.currentTimeMillis()-ts);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<ArticleReadRecordDto> getArticleReadRecordDto(Long memberId, Long createTime) {
        return articleReadRecordMapper.getArticleReadRecordDto(memberId, createTime);
    }

    @Override
    @Transactional
    public Boolean readArticleRequest(Long memberId, Long time, Long articleId) {
        ArticleReadRecord articleReadRecord=new ArticleReadRecord();
        articleReadRecord.setArticleId(articleId);
        articleReadRecord.setMemberId(memberId);
        articleReadRecord.setCreateTime(time);
        List<ArticleReadRecord> articleReadRecordList=articleReadRecordMapper.select(articleReadRecord);
        if (articleReadRecordList.isEmpty()||articleReadRecordList.size()>1){
            throw new InfoException("文章不存在或者已阅读");
        }
        articleReadRecord=articleReadRecordList.get(0);
        articleReadRecord.setType(1);
        articleReadRecordMapper.updateByPrimaryKeySelective(articleReadRecord);
        TaskPool taskPool=taskPoolMapper.getTaskPoolByArticleId(articleId,1);
        if (taskPool==null){
            throw new InfoException("任务池中没有该文章");
        }
        Double score = taskPool.getTotalScore()-taskPool.getSingleScore();
        if (score<0){
            throw new InfoException("任务池中该文章的剩余米币不足以支付阅读任务");
        }
        taskPool.setTotalScore(score);
        Article article=articleMapper.selectByPrimaryKey(articleId);
        OfficialAccount account=officalAccountMapper.selectByPrimaryKey(article.getOfficialAccountId());
        if (score<taskPool.getSingleScore()){
            taskPool.setTotalScore(0D);
            taskPool.setLimitDay(0L);
            taskPool.setNeedNumber(0);
            taskPool.setSingleScore(0D);
            taskPool.setIsPublishNow(0);
            memberScoreService.addMemberScore(account.getMemberId(), 6L,1,score.doubleValue(), UUIDGenerator.generate());
            JpushUtils.buildRequest("您发布的任务积分已不足，任务终止",account.getMemberId());
        }
        taskPoolMapper.updateByPrimaryKeySelective(taskPool);
        memberTaskHistoryService.addMemberTaskToHistory(memberId,6L, BigDecimal.valueOf(taskPool.getSingleScore()).multiply(taskPool.getRate()).doubleValue(),1,"阅读文章"+article.getTitle(),null,null);
        memberScoreService.addMemberScore(memberId,3L,1,BigDecimal.valueOf(taskPool.getSingleScore()).multiply(taskPool.getRate()).doubleValue(),UUIDGenerator.generate());
        return true;
    }

    @Override
    public Boolean pcreadArticleRequest(Long memberId, Long articleId) {
        ArticleReadRecord articleReadRecord=new ArticleReadRecord();
        articleReadRecord.setArticleId(articleId);
        articleReadRecord.setMemberId(memberId);
        articleReadRecord.setCreateTime(DateUtils.getUnixTimestamp());
        articleReadRecord.setType(1);
        TaskPool taskPool=taskPoolMapper.getTaskPoolByArticleId(articleId,1);
        if (taskPool==null){
            throw new InfoException("任务池中没有该文章");
        }
        Double score = taskPool.getTotalScore()-taskPool.getSingleScore();
        if (score<0){
            throw new InfoException("任务池中该文章的剩余米币不足以支付阅读任务");
        }
        articleReadRecordMapper.insertSelective(articleReadRecord);
        taskPool.setTotalScore(score);
        Article article=articleMapper.selectByPrimaryKey(articleId);
        OfficialAccount account=officalAccountMapper.selectByPrimaryKey(article.getOfficialAccountId());
        if (score<taskPool.getSingleScore()){
            taskPool.setTotalScore(0D);
            taskPool.setLimitDay(0L);
            taskPool.setIsPublishNow(0);
            memberScoreService.addMemberScore(account.getMemberId(), 6L,1,score.doubleValue(), UUIDGenerator.generate());
            JpushUtils.buildRequest("您发布的文章"+article.getTitle()+"阅读任务已经完成，任务终止",account.getMemberId());
        }
        taskPoolMapper.updateByPrimaryKeySelective(taskPool);
        memberTaskHistoryService.addMemberTaskToHistory(memberId,6L, BigDecimal.valueOf(taskPool.getSingleScore()).multiply(taskPool.getRate()).doubleValue(),1,"阅读文章"+article.getTitle(),null,null);
        memberScoreService.addMemberScore(memberId,3L,1,BigDecimal.valueOf(taskPool.getSingleScore()).multiply(taskPool.getRate()).doubleValue(),UUIDGenerator.generate());
        return true;
    }
}
