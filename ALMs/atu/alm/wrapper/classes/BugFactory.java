/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.activeX.ActiveXComponent;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class BugFactory
/*    */ {
/*    */   private ActiveXComponent almObject;
/*    */   private Dispatch bugFactory;
/*    */   
/*    */   public BugFactory(ActiveXComponent almObject)
/*    */   {
/* 15 */     this.almObject = almObject;
/* 16 */     this.bugFactory = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 20 */     Dispatch bugFactory = Dispatch.call(this.almObject, "BugFactory").toDispatch();
/*    */     
/* 22 */     return bugFactory;
/*    */   }
/*    */   
/*    */   public Bug addItem() {
/* 26 */     return new Bug(this.bugFactory);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\BugFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */