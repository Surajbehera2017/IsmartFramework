/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.ITestSet;
/*    */ import com.jacob.activeX.ActiveXComponent;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class TestSet
/*    */   implements ITestSet
/*    */ {
/*    */   private ActiveXComponent almObject;
/*    */   private Dispatch testSet;
/*    */   
/*    */   public TestSet(ActiveXComponent almObject, Dispatch testSet)
/*    */   {
/* 17 */     this.almObject = almObject;
/* 18 */     this.testSet = testSet;
/*    */   }
/*    */   
/*    */   public TSTestFactory getTSTestFactory() {
/* 22 */     return new TSTestFactory(this.almObject, this.testSet);
/*    */   }
/*    */   
/*    */   public String getName() {
/* 26 */     String name = Dispatch.call(this.testSet, "Name").getString();
/* 27 */     return name;
/*    */   }
/*    */   
/*    */   public AttachmentFactory getAttachments() {
/* 31 */     return new AttachmentFactory(this.testSet);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\TestSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */