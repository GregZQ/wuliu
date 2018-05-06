package com.df.domain;

import java.util.List;

/**
 * Created by Jone on 2018-04-06.
 */
public class PageBean {
    public static final Integer DEFAULTNUMS=10;
    public static final Integer BASICPAGE=1;
    private Integer start;

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    private Integer end;

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    private Integer currentPage;

    private Integer counts;

    private Integer per;

    private Integer totalPages;

    private List datas;
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getPer() {
        return per;
    }

    public void setPer(Integer per) {
        this.per = per;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public void pagIng() {
        this.start=currentPage-4;
        this.end=currentPage+5;
        if (this.start<=0){
            this.start=1;
            this.end=this.totalPages>10?10:this.totalPages;
        }
        if (this.end>this.totalPages){
            this.end=totalPages;
            this.start=((this.end-9)>=1)?this.end-9:1;
        }
        if (this.totalPages==0){
            this.start=0;
            this.end=0;
        }
    }

    public static PageBean checkPageBean(PageBean pageBean) {

        if (pageBean==null){
            PageBean newPageBean=new PageBean();

            pageBean.setPer(PageBean.DEFAULTNUMS);

            pageBean.setCurrentPage(PageBean.BASICPAGE);

            return pageBean;

        }

        if (pageBean.getCurrentPage()==null){
            pageBean.setCurrentPage(1);
        }else{
            if (pageBean.getCurrentPage()<=0)
                pageBean.setCurrentPage(PageBean.BASICPAGE);
        }
        if (pageBean.getPer()==null){
            pageBean.setPer(PageBean.DEFAULTNUMS);
        }else{
            if (pageBean.getPer()<=0)
                pageBean.setPer(PageBean.DEFAULTNUMS);
        }
        return  pageBean;

    }

    public static PageBean finishPageBean(List datas, Integer count,PageBean pageBean) {

        Integer nums=count%pageBean.getPer()==0?(count/pageBean.getPer())
                :(count/pageBean.getPer()+1);
        pageBean.setTotalPages(nums);

        pageBean.setCounts(count);
        pageBean.setDatas(datas);
        pageBean.pagIng();
        return  pageBean;
    }
}
