package generator;

import java.io.Serializable;

/**
 * b_accu2abm_tif
 * @author 
 */
public class BAccu2abmTifKey implements Serializable {
    private Integer tifSeq;

    private Long instanceId;

    private static final long serialVersionUID = 1L;

    public Integer getTifSeq() {
        return tifSeq;
    }

    public void setTifSeq(Integer tifSeq) {
        this.tifSeq = tifSeq;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
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
        BAccu2abmTifKey other = (BAccu2abmTifKey) that;
        return (this.getTifSeq() == null ? other.getTifSeq() == null : this.getTifSeq().equals(other.getTifSeq()))
            && (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTifSeq() == null) ? 0 : getTifSeq().hashCode());
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tifSeq=").append(tifSeq);
        sb.append(", instanceId=").append(instanceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}