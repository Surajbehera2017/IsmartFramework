package atu.alm.wrapper;

import atu.alm.wrapper.classes.AttachmentFactory;
import atu.alm.wrapper.enums.DefectPriority;
import atu.alm.wrapper.enums.DefectSeverity;
import atu.alm.wrapper.enums.DefectStatus;

public abstract interface IDefect
  extends HasAttachmentFeature
{
  public abstract void setDetectedBy(String paramString);
  
  public abstract void setAssignedTo(String paramString);
  
  public abstract void setPriority(DefectPriority paramDefectPriority);
  
  public abstract void setSeverity(DefectSeverity paramDefectSeverity);
  
  public abstract void setStatus(DefectStatus paramDefectStatus);
  
  public abstract void setSummary(String paramString);
  
  public abstract void setDetectionDate(String paramString);
  
  public abstract void setDescription(String paramString);
  
  public abstract void isReproducible(boolean paramBoolean);
  
  public abstract void setProject(String paramString);
  
  public abstract void save();
  
  public abstract int getDefectID();
  
  public abstract AttachmentFactory getAttachments();
}


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\IDefect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */