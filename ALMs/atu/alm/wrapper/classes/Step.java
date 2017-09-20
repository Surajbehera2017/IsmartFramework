/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.enums.StatusAs;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Step
/*    */ {
/*    */   private Dispatch step;
/*    */   
/*    */   public Step(Dispatch step)
/*    */   {
/* 16 */     this.step = step;
/*    */   }
/*    */   
/*    */   public void setStepName(String stepName) {
/* 20 */     Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_STEP_NAME", new Variant(stepName) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void setStatus(StatusAs as)
/*    */   {
/* 25 */     Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_STATUS", new Variant(as.getStatus()) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void setDescription(String description)
/*    */   {
/* 30 */     Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_DESCRIPTION", new Variant(description) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void setActual(String actual)
/*    */   {
/* 35 */     Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_ACTUAL", new Variant(actual) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void setExpected(String expected)
/*    */   {
/* 40 */     Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_EXPECTED", new Variant(expected) }, new int[1]);
/*    */   }
/*    */   
/*    */   public void post()
/*    */   {
/* 45 */     Dispatch.call(this.step, "Post");
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\Step.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */