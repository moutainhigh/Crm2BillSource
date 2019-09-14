package generator;

import java.io.Serializable;

/**
 * tif_disct_object_id_contrast
 * @author 
 */
public class TifDisctObjectIdContrast extends TifDisctObjectIdContrastKey implements Serializable {
    private String disctObjectName97;

    private Long disctObjectIdBill;

    private String disctObjectNameBill;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getDisctObjectName97() {
        return disctObjectName97;
    }

    public void setDisctObjectName97(String disctObjectName97) {
        this.disctObjectName97 = disctObjectName97;
    }

    public Long getDisctObjectIdBill() {
        return disctObjectIdBill;
    }

    public void setDisctObjectIdBill(Long disctObjectIdBill) {
        this.disctObjectIdBill = disctObjectIdBill;
    }

    public String getDisctObjectNameBill() {
        return disctObjectNameBill;
    }

    public void setDisctObjectNameBill(String disctObjectNameBill) {
        this.disctObjectNameBill = disctObjectNameBill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        TifDisctObjectIdContrast other = (TifDisctObjectIdContrast) that;
        return (this.getDisctId97() == null ? other.getDisctId97() == null : this.getDisctId97().equals(other.getDisctId97()))
            && (this.getDisctObjectId97() == null ? other.getDisctObjectId97() == null : this.getDisctObjectId97().equals(other.getDisctObjectId97()))
            && (this.getDisctObjectName97() == null ? other.getDisctObjectName97() == null : this.getDisctObjectName97().equals(other.getDisctObjectName97()))
            && (this.getDisctObjectIdBill() == null ? other.getDisctObjectIdBill() == null : this.getDisctObjectIdBill().equals(other.getDisctObjectIdBill()))
            && (this.getDisctObjectNameBill() == null ? other.getDisctObjectNameBill() == null : this.getDisctObjectNameBill().equals(other.getDisctObjectNameBill()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDisctId97() == null) ? 0 : getDisctId97().hashCode());
        result = prime * result + ((getDisctObjectId97() == null) ? 0 : getDisctObjectId97().hashCode());
        result = prime * result + ((getDisctObjectName97() == null) ? 0 : getDisctObjectName97().hashCode());
        result = prime * result + ((getDisctObjectIdBill() == null) ? 0 : getDisctObjectIdBill().hashCode());
        result = prime * result + ((getDisctObjectNameBill() == null) ? 0 : getDisctObjectNameBill().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", disctObjectName97=").append(disctObjectName97);
        sb.append(", disctObjectIdBill=").append(disctObjectIdBill);
        sb.append(", disctObjectNameBill=").append(disctObjectNameBill);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}