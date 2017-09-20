/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.collection.ListWrapper;
/*    */ import com.jacob.activeX.ActiveXComponent;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TSTestFactory
/*    */ {
/*    */   private Dispatch testSet;
/*    */   private Dispatch tsTestFactory;
/*    */   
/*    */   public TSTestFactory(ActiveXComponent almObject, Dispatch testSet)
/*    */   {
/* 18 */     this.testSet = testSet;
/* 19 */     this.tsTestFactory = init();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private Dispatch init()
/*    */   {
/* 27 */     Dispatch tsTestFactory = Dispatch.get(this.testSet, "TSTestFactory").toDispatch();
/*    */     
/* 29 */     return tsTestFactory;
/*    */   }
/*    */   
/*    */   public ListWrapper<TSTest> getNewList() {
/* 33 */     Dispatch listOfTests = Dispatch.call(this.tsTestFactory, "NewList", new Object[] { "" }).toDispatch();
/*    */     
/* 35 */     int count = Dispatch.call(listOfTests, "Count").getInt();
/* 36 */     ListWrapper<TSTest> listWrapper = new ListWrapper();
/* 37 */     for (int i = 1; i <= count; i++) {
/* 38 */       Dispatch dispatchTSTest = Dispatch.call(listOfTests, "Item", new Object[] { Integer.valueOf(i) }).toDispatch();
/*    */       
/* 40 */       TSTest tsTest = new TSTest(dispatchTSTest);
/* 41 */       listWrapper.add(tsTest);
/*    */     }
/* 43 */     return listWrapper;
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\TSTestFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */