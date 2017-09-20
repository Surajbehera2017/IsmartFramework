/*    */ package atu.alm.wrapper.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum DefectStatus
/*    */ {
/*  8 */   NEW("New"),  OPEN("Open"),  REJECTED("Rejected"),  FIXED("Fixed"),  REOPEN("Reopen"), 
/*  9 */   CLOSED("Closed");
/*    */   
/*    */   private String status;
/*    */   
/* 13 */   private DefectStatus(String status) { setStatus(status); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getStatus()
/*    */   {
/* 20 */     return this.status;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setStatus(String status)
/*    */   {
/* 28 */     this.status = status;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\enums\DefectStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */