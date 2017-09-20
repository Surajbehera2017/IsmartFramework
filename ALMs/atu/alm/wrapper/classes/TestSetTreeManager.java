/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.exceptions.ALMServiceException;
/*    */ import com.jacob.activeX.ActiveXComponent;
/*    */ import com.jacob.com.ComFailException;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestSetTreeManager
/*    */ {
/*    */   private ActiveXComponent almObject;
/*    */   private Dispatch testSetTreeManager;
/*    */   private static final String ROOT = "Root";
/*    */   
/*    */   public TestSetTreeManager(ActiveXComponent almObject)
/*    */   {
/* 19 */     this.almObject = almObject;
/* 20 */     this.testSetTreeManager = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 24 */     Dispatch testSetTreeManager = Dispatch.get(this.almObject, "TestSetTreeManager").toDispatch();
/*    */     
/* 26 */     return testSetTreeManager;
/*    */   }
/*    */   
/*    */   public TestSetFolder getNodeByPath(String testSetFolderPath) throws ALMServiceException
/*    */   {
/*    */     Dispatch testSetFolder;
/*    */     try {
/* 33 */       testSetFolder = Dispatch.call(this.testSetTreeManager, "NodeByPath", new Object[] { "Root\\" + testSetFolderPath }).toDispatch();
/*    */     }
/*    */     catch (ComFailException e)
/*    */     {
/* 37 */       throw new ALMServiceException("The Given Test Set Folder Path \"" + testSetFolderPath + "\" Not Found");
/*    */     }
/*    */     
/* 40 */     return new TestSetFolder(this.almObject, testSetFolder);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\TestSetTreeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */