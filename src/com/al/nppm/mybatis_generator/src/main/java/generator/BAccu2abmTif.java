package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * b_accu2abm_tif
 * @author 
 */
public class BAccu2abmTif extends BAccu2abmTifKey implements Serializable {
    private Long instanceType;

    private Date insertDate;

    private Date stateDate;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Long getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(Long instanceType) {
        this.instanceType = instanceType;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        BAccu2abmTif other = (BAccu2abmTif) that;
        return (this.getTifSeq() == null ? other.getTifSeq() == null : this.getTifSeq().equals(other.getTifSeq()))
            && (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getInstanceType() == null ? other.getInstanceType() == null : this.getInstanceType().equals(other.getInstanceType()))
            && (this.getInsertDate() == null ? other.getInsertDate() == null : this.getInsertDate().equals(other.getInsertDate()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTifSeq() == null) ? 0 : getTifSeq().hashCode());
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getInstanceType() == null) ? 0 : getInstanceType().hashCode());
        result = prime * result + ((getInsertDate() == null) ? 0 : getInsertDate().hashCode());
        result = prime * result + ((getStateDate() == null) ? 0 : getStateDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", instanceType=").append(instanceType);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}