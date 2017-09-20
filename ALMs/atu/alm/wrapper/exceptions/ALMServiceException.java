/*    */ package atu.alm.wrapper.exceptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ALMServiceException
/*    */   extends Exception
/*    */ {
/*    */   private String message;
/*    */   
/*    */ 
/*    */   public ALMServiceException(String message)
/*    */   {
/* 13 */     super(message);
/* 14 */     this.message = message;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return "ALM Service Wrapper Exception Details:  " + this.message;
/*    */   }
/*    */ }

