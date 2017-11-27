package com.example.martian.bean;

import java.util.List;

/**
 * @author martian on 2017/11/22.
 */

public class IDRecognitionTecentModel {

  private List<ResultListBean> result_list;

  public List<ResultListBean> getResult_list() {
    return result_list;
  }

  public void setResult_list(List<ResultListBean> result_list) {
    this.result_list = result_list;
  }

  public static class ResultListBean {
    /**
     * code : 0
     * message : success
     * filename : 1.jpg
     * data : {"authority":"某市派出所","valid_date":"2012.01.01-2022.01.01","authority_confidence_all":[42,36,40,49,41],"valid_date_confidence_all":[44,50,63,44,47,42,43,53,48,52,44,47,48,45,50,58]}
     */

    private int code;
    private String message;
    private String filename;
    private DataBean data;

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getFilename() {
      return filename;
    }

    public void setFilename(String filename) {
      this.filename = filename;
    }

    public DataBean getData() {
      return data;
    }

    public void setData(DataBean data) {
      this.data = data;
    }

    public static class DataBean {
      /**
       * authority : 某市派出所
       * valid_date : 2012.01.01-2022.01.01
       * authority_confidence_all : [42,36,40,49,41]
       * valid_date_confidence_all : [44,50,63,44,47,42,43,53,48,52,44,47,48,45,50,58]
       */

      private String authority;
      private String valid_date;
      private List<Integer> authority_confidence_all;
      private List<Integer> valid_date_confidence_all;

      public String getAuthority() {
        return authority;
      }

      public void setAuthority(String authority) {
        this.authority = authority;
      }

      public String getValid_date() {
        return valid_date;
      }

      public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
      }

      public List<Integer> getAuthority_confidence_all() {
        return authority_confidence_all;
      }

      public void setAuthority_confidence_all(List<Integer> authority_confidence_all) {
        this.authority_confidence_all = authority_confidence_all;
      }

      public List<Integer> getValid_date_confidence_all() {
        return valid_date_confidence_all;
      }

      public void setValid_date_confidence_all(List<Integer> valid_date_confidence_all) {
        this.valid_date_confidence_all = valid_date_confidence_all;
      }
    }
  }
}
