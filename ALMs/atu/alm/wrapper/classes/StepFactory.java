/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class StepFactory
/*    */ {
/*    */   private Dispatch stepFactory;
/*    */   private Dispatch run;
/*    */   
/*    */   public StepFactory(Dispatch run)
/*    */   {
/* 14 */     this.run = run;
/* 15 */     this.stepFactory = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 19 */     Dispatch stepFactory = Dispatch.get(this.run, "StepFactory").toDispatch();
/* 20 */     return stepFactory;
/*    */   }
/*    */   
/*    */   public Step addItem() {
/* 24 */     Dispatch step = Dispatch.call(this.stepFactory, "AddItem", new Object[] { "Null" }).toDispatch();
/*    */     
/* 26 */     return new Step(step);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\StepFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */