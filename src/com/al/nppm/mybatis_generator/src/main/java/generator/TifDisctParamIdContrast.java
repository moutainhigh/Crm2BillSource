package generator;

import java.io.Serializable;

/**
 * tif_disct_param_id_contrast
 * @author 
 */
public class TifDisctParamIdContrast implements Serializable {
    private Long idNo;

    private Long disctId97;

    private String disctParamId97;

    private String disctParamName97;

    private Long disctParamIdBill;

    private String disctParamNameBill;

    private String allowFlag;

    private String description;

    private String isRent;

    private Long isNextChange;

    private static final long serialVersionUID = 1L;

    public Long getIdNo() {
        return idNo;
    }

    public void setIdNo(Long idNo) {
        this.idNo = idNo;
    }

    public Long getDisctId97() {
        return disctId97;
    }

    public void setDisctId97(Long disctId97) {
        this.disctId97 = disctId97;
    }

    public String getDisctParamId97() {
        return disctParamId97;
    }

    public void setDisctParamId97(String disctParamId97) {
        this.disctParamId97 = disctParamId97;
    }

    public String getDisctParamName97() {
        return disctParamName97;
    }

    public void setDisctParamName97(String disctParamName97) {
        this.disctParamName97 = disctParamName97;
    }

    public Long getDisctParamIdBill() {
        return disctParamIdBill;
    }

    public void setDisctParamIdBill(Long disctParamIdBill) {
        this.disctParamIdBill = disctParamIdBill;
    }

    public String getDisctParamNameBill() {
        return disctParamNameBill;
    }

    public void setDisctParamNameBill(String disctParamNameBill) {
        this.disctParamNameBill = disctParamNameBill;
    }

    public String getAllowFlag() {
        return allowFlag;
    }

    public void setAllowFlag(String allowFlag) {
        this.allowFlag = allowFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsRent() {
        return isRent;
    }

    public void setIsRent(String isRent) {
        this.isRent = isRent;
    }

    public Long getIsNextChange() {
        return isNextChange;
    }

    public void setIsNextChange(Long isNextChange) {
        this.isNextChange = isNextChange;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TifDisctParamIdContrast other = (TifDisctParamIdContrast) that;
        return (this.getIdNo() == null ? other.getIdNo() == null : this.getIdNo().equals(other.getIdNo()))
            && (this.getDisctId97() == null ? other.getDisctId97() == null : this.getDisctId97().equals(other.getDisctId97()))
            && (this.getDisctParamId97() == null ? other.getDisctParamId97() == null : this.getDisctParamId97().equals(other.getDisctParamId97()))
            && (this.getDisctParamName97() == null ? other.getDisctParamName97() == null : this.getDisctParamName97().equals(other.getDisctParamName97()))
            && (this.getDisctParamIdBill() == null ? other.getDisctParamIdBill() == null : this.getDisctParamIdBill().equals(other.getDisctParamIdBill()))
            && (this.getDisctParamNameBill() == null ? other.getDisctParamNameBill() == null : this.getDisctParamNameBill().equals(other.getDisctParamNameBill()))
            && (this.getAllowFlag() == null ? other.getAllowFlag() == null : this.getAllowFlag().equals(other.getAllowFlag()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getIsRent() == null ? other.getIsRent() == null : this.getIsRent().equals(other.getIsRent()))
            && (this.getIsNextChange() == null ? other.getIsNextChange() == null : this.getIsNextChange().equals(other.getIsNextChange()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdNo() == null) ? 0 : getIdNo().hashCode());
        result = prime * result + ((getDisctId97() == null) ? 0 : getDisctId97().hashCode());
        result = prime * result + ((getDisctParamId97() == null) ? 0 : getDisctParamId97().hashCode());
        result = prime * result + ((getDisctParamName97() == null) ? 0 : getDisctParamName97().hashCode());
        result = prime * result + ((getDisctParamIdBill() == null) ? 0 : getDisctParamIdBill().hashCode());
        result = prime * result + ((getDisctParamNameBill() == null) ? 0 : getDisctParamNameBill().hashCode());
        result = prime * result + ((getAllowFlag() == null) ? 0 : getAllowFlag().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getIsRent() == null) ? 0 : getIsRent().hashCode());
        result = prime * result + ((getIsNextChange() == null) ? 0 : getIsNextChange().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idNo=").append(idNo);
        sb.append(", disctId97=").append(disctId97);
        sb.append(", disctParamId97=").append(disctParamId97);
        sb.append(", disctParamName97=").append(disctParamName97);
        sb.append(", disctParamIdBill=").append(disctParamIdBill);
        sb.append(", disctParamNameBill=").append(disctParamNameBill);
        sb.append(", allowFlag=").append(allowFlag);
        sb.append(", description=").append(description);
        sb.append(", isRent=").append(isRent);
        sb.append(", isNextChange=").append(isNextChange);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}