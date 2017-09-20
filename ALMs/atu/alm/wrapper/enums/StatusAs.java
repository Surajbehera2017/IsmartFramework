/*    */ package atu.alm.wrapper.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum StatusAs
/*    */ {
/*  9 */   NO_RUN("No Run"),  PASSED("Passed"),  FAILED("Failed"),  BLOCKED("Blocked"),  N_A("N/A"), 
/* 10 */   NOT_COMPLETED("Not Completed");
/*    */   
/*    */   private String status;
/*    */   
/* 14 */   private StatusAs(String status) { setStatus(status); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getStatus()
/*    */   {
/* 21 */     return this.status;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setStatus(String status)
/*    */   {
/* 29 */     this.status = status;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\enums\StatusAs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */