package generator;

import java.io.Serializable;

/**
 * intf_serv_cust_change_contrast
 * @author 
 */
public class IntfServCustChangeContrast implements Serializable {
    private Long archGrpId;

    private Long servId;

    private Long oldCustId;

    private Long newCustId;

    private String changeDate;

    private static final long serialVersionUID = 1L;

    public Long getArchGrpId() {
        return archGrpId;
    }

    public void setArchGrpId(Long archGrpId) {
        this.archGrpId = archGrpId;
    }

    public Long getServId() {
        return servId;
    }

    public void setServId(Long servId) {
        this.servId = servId;
    }

    public Long getOldCustId() {
        return oldCustId;
    }

    public void setOldCustId(Long oldCustId) {
        this.oldCustId = oldCustId;
    }

    public Long getNewCustId() {
        return newCustId;
    }

    public void setNewCustId(Long newCustId) {
        this.newCustId = newCustId;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
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
        IntfServCustChangeContrast other = (IntfServCustChangeContrast) that;
        return (this.getArchGrpId() == null ? other.getArchGrpId() == null : this.getArchGrpId().equals(other.getArchGrpId()))
            && (this.getServId() == null ? other.getServId() == null : this.getServId().equals(other.getServId()))
            && (this.getOldCustId() == null ? other.getOldCustId() == null : this.getOldCustId().equals(other.getOldCustId()))
            && (this.getNewCustId() == null ? other.getNewCustId() == null : this.getNewCustId().equals(other.getNewCustId()))
            && (this.getChangeDate() == null ? other.getChangeDate() == null : this.getChangeDate().equals(other.getChangeDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArchGrpId() == null) ? 0 : getArchGrpId().hashCode());
        result = prime * result + ((getServId() == null) ? 0 : getServId().hashCode());
        result = prime * result + ((getOldCustId() == null) ? 0 : getOldCustId().hashCode());
        result = prime * result + ((getNewCustId() == null) ? 0 : getNewCustId().hashCode());
        result = prime * result + ((getChangeDate() == null) ? 0 : getChangeDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", archGrpId=").append(archGrpId);
        sb.append(", servId=").append(servId);
        sb.append(", oldCustId=").append(oldCustId);
        sb.append(", newCustId=").append(newCustId);
        sb.append(", changeDate=").append(changeDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}