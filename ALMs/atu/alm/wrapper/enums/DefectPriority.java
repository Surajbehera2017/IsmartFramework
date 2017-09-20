/*    */ package atu.alm.wrapper.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum DefectPriority
/*    */ {
/*  8 */   LOW("1-Low"),  MEDIUM("2-Medium"),  HIGH("3-High"),  VERY_HIGH("4-Very High"),  URGENT("5-Urgent");
/*    */   
/*    */   private String priority;
/*    */   
/*    */   private DefectPriority(String priority) {
/* 13 */     setPriority(priority);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getPriority()
/*    */   {
/* 20 */     return this.priority;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setPriority(String priority)
/*    */   {
/* 28 */     this.priority = priority;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\enums\DefectPriority.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */