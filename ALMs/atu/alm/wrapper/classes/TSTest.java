/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.ITestCase;
/*    */ import atu.alm.wrapper.enums.StatusAs;
/*    */ import com.jacob.activeX.ActiveXComponent;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class TSTest
/*    */   implements ITestCase
/*    */ {
/*    */   private Dispatch test;
/*    */   private Dispatch tsTest;
/*    */   
/*    */   public TSTest(ActiveXComponent almObject, Dispatch test)
/*    */   {
/* 18 */     this.test = test;
/* 19 */     this.tsTest = init();
/*    */   }
/*    */   
/*    */   public TSTest(Dispatch tsTest) {
/* 23 */     this.tsTest = tsTest;
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 27 */     Dispatch tsTest = Dispatch.call(this.test, "Item", new Object[] { Integer.valueOf(1) }).toDispatch();
/* 28 */     return tsTest;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 32 */     String name = Dispatch.call(this.tsTest, "Name").getString();
/* 33 */     return name;
/*    */   }
/*    */   
/*    */   public String getTestName() {
/* 37 */     String testName = Dispatch.call(this.tsTest, "TestName").getString();
/* 38 */     return testName;
/*    */   }
/*    */   
/*    */   public void putStatus(StatusAs as) {
/* 42 */     Dispatch.put(this.tsTest, "Status", as.getStatus().trim());
/*    */   }
/*    */   
/*    */   public RunFactory getRunFactory() {
/* 46 */     return new RunFactory(this.tsTest);
/*    */   }
/*    */   
/*    */   public void post() {
/* 50 */     Dispatch.call(this.tsTest, "Post");
/*    */   }
/*    */   
/*    */   public AttachmentFactory getAttachments()
/*    */   {
/* 55 */     return new AttachmentFactory(this.tsTest);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\TSTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */