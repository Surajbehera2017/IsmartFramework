/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   private Dispatch tsTest;
/*    */   private Dispatch test;
/*    */   
/*    */   public Test(Dispatch tsTest)
/*    */   {
/* 14 */     this.tsTest = tsTest;
/* 15 */     this.test = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 19 */     Dispatch test = Dispatch.get(this.tsTest, "Test").toDispatch();
/* 20 */     return test;
/*    */   }
/*    */   
/*    */   public DesignStepFactory getDesignStepFactory() {
/* 24 */     return new DesignStepFactory(this.test);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\Test.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */