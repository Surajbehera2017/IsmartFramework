/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class DesignStepFactory
/*    */ {
/*    */   private Dispatch test;
/*    */   
/*    */   public DesignStepFactory(Dispatch test)
/*    */   {
/* 13 */     this.test = test;
/* 14 */     init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 18 */     Dispatch designStepFactory = Dispatch.get(this.test, "DesignStepFactory").toDispatch();
/*    */     
/* 20 */     return designStepFactory;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\DesignStepFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */