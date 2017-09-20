/*    */ package atu.alm.wrapper.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum DefectSeverity
/*    */ {
/*  8 */   LOW("1-Low"),  MEDIUM("2-Medium"),  HIGH("3-High"),  VERY_HIGH("4-Very High"),  URGENT("5-Urgent");
/*    */   
/*    */   private String severity;
/*    */   
/*    */   private DefectSeverity(String severity) {
/* 13 */     setSeverity(severity);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getSeverity()
/*    */   {
/* 20 */     return this.severity;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setSeverity(String severity)
/*    */   {
/* 28 */     this.severity = severity;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\enums\DefectSeverity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */