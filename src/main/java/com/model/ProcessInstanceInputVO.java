package com.model;

import com.alibaba.fastjson.JSON;
import com.taobao.api.internal.util.json.JSONWriter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 令久
 * @date 2018/08/22
 * 审批实例
 *
 * 模型地址文档 https://ding-doc.dingtalk.com/doc#/serverapi2/cmct1a
 */
public class ProcessInstanceInputVO {
    /**
     * 审批人列表
     */
    private String approvers;

    /**
     * 抄送人列表
     */
    private String ccList;

    /**
     * 抄送时机
     */
    private String ccPosition;

    /**
     * 审批发起人部门id
     */
    private Long deptId;

    /**
     * 单行输入框、多行输入框的表单数据
     */
    private List<TextForm> textForms;

    /**
     * 数字输入框
     */
    private List<NumberForm> numberForms;

    /**
     * 图片表单数据
     */
    private List<PictureForm> pictureForms;

    /**
     * 明细表单数据
     */
    private List<DetailForm> detailForms;

    /**
     * 审批发起人
     */
    private String originatorUserId;

    public static class TextForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值
         */
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //可废弃，金额输入框可以直接用字符串。亲测可用
    public static class NumberForm{
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值
         */
        private Integer value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public static class PictureForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 表单值，数组格式
         */
        private List<String> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }

    public static class DetailForm {
        /**
         * 表单控件名称
         */
        private String name;

        /**
         * 明细里的文本控件列表
         */
        private List<TextForm> textForms;

        /**
         * 明细里的图片控件列表
         */
        private List<PictureForm> pictureForms;

        /**
         * 数字输入框
         */
        private List<NumberForm> numberForms;

        public List<NumberForm> getNumberForms() {
            return numberForms;
        }

        public void setNumberForms(List<NumberForm> numberForms) {
            this.numberForms = numberForms;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TextForm> getTextForms() {
            return textForms;
        }

        public void setTextForms(List<TextForm> textForms) {
            this.textForms = textForms;
        }

        public List<PictureForm> getPictureForms() {
            return pictureForms;
        }

        public void setPictureForms(List<PictureForm> pictureForms) {
            this.pictureForms = pictureForms;
        }
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers;
    }

    public String getCcList() {
        return ccList;
    }

    public void setCcList(String ccList) {
        this.ccList = ccList;
    }

    public String getCcPosition() {
        return ccPosition;
    }

    public void setCcPosition(String ccPosition) {
        this.ccPosition = ccPosition;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<TextForm> getTextForms() {
        return textForms;
    }

    public void setTextForms(List<TextForm> textForms) {
        this.textForms = textForms;
    }

    public List<PictureForm> getPictureForms() {
        return pictureForms;
    }

    public void setPictureForms(List<PictureForm> pictureForms) {
        this.pictureForms = pictureForms;
    }

    public List<DetailForm> getDetailForms() {
        return detailForms;
    }

    public void setDetailForms(List<DetailForm> detailForms) {
        this.detailForms = detailForms;
    }

    public String getOriginatorUserId() {
        return originatorUserId;
    }

    public void setOriginatorUserId(String originatorUserId) {
        this.originatorUserId = originatorUserId;
    }

    public List<NumberForm> getNumberForms() {
        return numberForms;
    }

    public void setNumberForms(List<NumberForm> numberForms) {
        this.numberForms = numberForms;
    }

    /**
     * 生成FormComponentValueVo，用于调用发起审批实例的接口
     * @return
     */
    public String generateForms() {
        List<FormComponentValueVo> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(textForms)) {
            for (TextForm textForm : textForms) {
                result.add(generateFormWithTextForm(textForm));
            }
        }

        if (!CollectionUtils.isEmpty(pictureForms)) {
            for (PictureForm pictureForm : pictureForms) {
                result.add(generateFormWithPictureForm(pictureForm));
            }
        }
        if (!CollectionUtils.isEmpty(numberForms)) {
            for (NumberForm numberForm : numberForms) {
                result.add(generateFormWithNumberForm(numberForm));
            }
        }

        if (!CollectionUtils.isEmpty(detailForms)) {
            for (DetailForm detailForm : detailForms) {
                result.add(generateFormWithDetailForm(detailForm));
            }
        }
        return (new JSONWriter(false, false, true)).write(result);
    }

    private FormComponentValueVo generateFormWithNumberForm(NumberForm numberForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(numberForm.getName());
        form.setValue(numberForm.getValue());
        return form;
    }

    private FormComponentValueVo generateFormWithTextForm(TextForm textForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(textForm.getName());
        form.setValue(textForm.getValue());
        return form;
    }

    private FormComponentValueVo generateFormWithPictureForm(PictureForm pictureForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(pictureForm.getName());
        form.setValue(JSON.toJSONString(pictureForm.getValue()));
        return form;
    }

    private FormComponentValueVo generateFormWithDetailForm(DetailForm detailForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(detailForm.getName());

        List<FormComponentValueVo> forms = new ArrayList<>();
        if (!CollectionUtils.isEmpty(detailForm.getTextForms())) {
            for (TextForm textForm : detailForm.getTextForms()) {
                forms.add(generateFormWithTextForm(textForm));
            }
        }

        if (!CollectionUtils.isEmpty(detailForm.pictureForms)) {
            for (PictureForm pictureForm : detailForm.pictureForms) {
                forms.add(generateFormWithPictureForm(pictureForm));
            }
        }

        form.setValue(JSON.toJSONString(Arrays.asList(forms)));

        return form;
    }
}
