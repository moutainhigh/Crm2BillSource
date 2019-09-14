package generator;

import java.io.Serializable;

/**
 * tif_disct_object_id_contrast
 * @author 
 */
public class TifDisctObjectIdContrastKey implements Serializable {
    private Long disctId97;

    private String disctObjectId97;

    private static final long serialVersionUID = 1L;

    public Long getDisctId97() {
        return disctId97;
    }

    public void setDisctId97(Long disctId97) {
        this.disctId97 = disctId97;
    }

    public String getDisctObjectId97() {
        return disctObjectId97;
    }

    public void setDisctObjectId97(String disctObjectId97) {
        this.disctObjectId97 = disctObjectId97;
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
        TifDisctObjectIdContrastKey other = (TifDisctObjectIdContrastKey) that;
        return (this.getDisctId97() == null ? other.getDisctId97() == null : this.getDisctId97().equals(other.getDisctId97()))
            && (this.getDisctObjectId97() == null ? other.getDisctObjectId97() == null : this.getDisctObjectId97().equals(other.getDisctObjectId97()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDisctId97() == null) ? 0 : getDisctId97().hashCode());
        result = prime * result + ((getDisctObjectId97() == null) ? 0 : getDisctObjectId97().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", disctId97=").append(disctId97);
        sb.append(", disctObjectId97=").append(disctObjectId97);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}