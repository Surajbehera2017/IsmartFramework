/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.exceptions.ALMServiceException;
/*    */ import com.jacob.activeX.ActiveXComponent;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestSetFolder
/*    */ {
/*    */   private ActiveXComponent almObject;
/*    */   private Dispatch testSetFolder;
/*    */   
/*    */   public TestSetFolder(ActiveXComponent almObject, Dispatch testSetFolder)
/*    */   {
/* 17 */     this.almObject = almObject;
/* 18 */     this.testSetFolder = testSetFolder;
/*    */   }
/*    */   
/*    */   public TestSet findTestSet(String testSetName, int searchTestSetID)
/*    */     throws ALMServiceException
/*    */   {
/* 24 */     Dispatch listOfTestSet = Dispatch.call(this.testSetFolder, "FindTestSets", new Object[] { testSetName, Boolean.valueOf(true), null }).toDispatch();
/*    */     
/* 26 */     Dispatch testSet = null;
/*    */     try {
/* 28 */       int count = Dispatch.call(listOfTestSet, "Count").getInt();
/*    */       
/* 30 */       for (int i = 1; i <= count; i++) {
/* 31 */         testSet = Dispatch.call(listOfTestSet, "Item", new Object[] { Integer.valueOf(i) }).toDispatch();
/* 32 */         int testSetID = Dispatch.call(testSet, "ID").getInt();
/* 33 */         //if (searchTestSetID == testSetID) {
/* 34 */           return new TestSet(this.almObject, testSet);
/*    */         //}
/*    */       }
/* 37 */       throw new ALMServiceException("The Given Test Set Name \"" + testSetName + "\" Not Found");
/*    */     }
/*    */     catch (NullPointerException e) {
/* 40 */       throw new ALMServiceException("The Given Test Set Name \"" + testSetName + "\" Not Found ");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getCount()
/*    */   {
/* 49 */     int count = Dispatch.call(this.testSetFolder, "Count").getInt();
/* 50 */     return count;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 54 */     String name = Dispatch.call(this.testSetFolder, "Name").getString();
/* 55 */     return name;
/*    */   }
/*    */   
/*    */   public String getPath() {
/* 59 */     String path = Dispatch.call(this.testSetFolder, "Path").getString();
/* 60 */     return path;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\TestSetFolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */