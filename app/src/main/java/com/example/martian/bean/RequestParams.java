package com.example.martian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author martian on 2017/11/21.
 */

public class RequestParams implements Serializable{

  private List<InputsBean> inputs;

  public List<InputsBean> getInputs() {
    return inputs;
  }

  public void setInputs(List<InputsBean> inputs) {
    this.inputs = inputs;
  }

  public static class InputsBean {
    /**
     * image : {"dataType":50,"dataValue":"图片二进制数据的base64编码"}
     * configure : {"dataType":50,"dataValue":"{\"side\":\"face\"}"}
     */

    private ImageBean image;
    private ConfigureBean configure;

    public ImageBean getImage() {
      return image;
    }

    public void setImage(ImageBean image) {
      this.image = image;
    }

    public ConfigureBean getConfigure() {
      return configure;
    }

    public void setConfigure(ConfigureBean configure) {
      this.configure = configure;
    }

    public static class ImageBean {
      /**
       * dataType : 50
       * dataValue : 图片二进制数据的base64编码
       */

      private int dataType;
      private String dataValue;

      public int getDataType() {
        return dataType;
      }

      public void setDataType(int dataType) {
        this.dataType = dataType;
      }

      public String getDataValue() {
        return dataValue;
      }

      public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
      }
    }

    public static class ConfigureBean {
      /**
       * dataType : 50
       * dataValue : {"side":"face"}
       */

      private int dataType;
      private String dataValue;

      public int getDataType() {
        return dataType;
      }

      public void setDataType(int dataType) {
        this.dataType = dataType;
      }

      public String getDataValue() {
        return dataValue;
      }

      public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
      }
    }
  }
}
