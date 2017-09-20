/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.IDefect;
/*    */ import atu.alm.wrapper.enums.DefectPriority;
/*    */ import atu.alm.wrapper.enums.DefectSeverity;
/*    */ import atu.alm.wrapper.enums.DefectStatus;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Bug
/*    */   implements IDefect
/*    */ {
/*    */   private Dispatch bugFactory;
/*    */   private Dispatch bug;
/*    */   
/*    */   public Bug(Dispatch bugFactory)
/*    */   {
/* 20 */     this.bugFactory = bugFactory;
/* 21 */     this.bug = init();
/*    */   }
/*    */   
/*    */   public AttachmentFactory getAttachments() {
/* 25 */     return new AttachmentFactory(this.bug);
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 29 */     Dispatch bug = Dispatch.call(this.bugFactory, "AddItem", new Object[] { "" }).toDispatch();
/* 30 */     return bug;
/*    */   }
/*    */   
/*    */   public void setAssignedTo(String assignedTo) {
/* 34 */     Dispatch.put(this.bug, "AssignedTo", assignedTo);
/*    */   }
/*    */   
/*    */   public void setSeverity(DefectSeverity severity) {
/* 38 */     Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_SEVERITY", new Variant(severity.getSeverity()) }, new int[1]);
/*    */   }
/*    */   
/*    */ 
/*    */   public void setDescription(String description)
/*    */   {
/* 44 */     Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_DESCRIPTION", new Variant(description) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void setProject(String project)
/*    */   {
/* 49 */     Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_PROJECT", new Variant(project) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void isReproducible(boolean isReproducible) {
/*    */     String reproducible;
/*    */   //  String reproducible;
/* 55 */     if (isReproducible) {
/* 56 */       reproducible = "Y";
/*    */     } else {
/* 58 */       reproducible = "N";
/*    */     }
/* 60 */     Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_REPRODUCIBLE", new Variant(reproducible) }, new int[1]);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setDetectionDate(String date)
/*    */   {
/* 68 */     Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_DETECTION_DATE", new Variant(date) }, new int[1]);
/*    */   }
/*    */   
/*    */   public int getDefectID()
/*    */   {
/* 73 */     int id = Dispatch.call(this.bug, "ID").getInt();
/* 74 */     return id;
/*    */   }
/*    */   
/*    */   public void setStatus(DefectStatus status) {
/* 78 */     Dispatch.put(this.bug, "Status", status.getStatus());
/*    */   }
/*    */   
/*    */   public void setPriority(DefectPriority priority) {
/* 82 */     Dispatch.put(this.bug, "Priority", priority.getPriority());
/*    */   }
/*    */   
/*    */   public void setSummary(String summary) {
/* 86 */     Dispatch.put(this.bug, "Summary", summary);
/*    */   }
/*    */   
/*    */   public void setDetectedBy(String detectedBy) {
/* 90 */     Dispatch.put(this.bug, "DetectedBy", detectedBy);
/*    */   }
/*    */   
/*    */   public void save() {
/* 94 */     Dispatch.call(this.bug, "Post");
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\Bug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */