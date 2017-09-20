/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class RunFactory
/*    */ {
/*    */   private Dispatch runFactory;
/*    */   private Dispatch tsTest;
/*    */   public static Dispatch addedRun;
/*    */   public RunFactory(Dispatch tsTest)
/*    */   {
/* 14 */     this.tsTest = tsTest;
/* 15 */     this.runFactory = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 19 */     Dispatch runFactory = Dispatch.get(this.tsTest, "RunFactory").toDispatch();
/* 20 */     return runFactory;
/*    */   }
/*    */   
/*    */   public Run addItem() {
/* 24 */     Dispatch run = Dispatch.call(this.runFactory, "AddItem", new Object[] { "Null" }).toDispatch();
/*    */     addedRun = run;
/* 26 */     return new Run(run);
/*    */   }


/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\RunFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */