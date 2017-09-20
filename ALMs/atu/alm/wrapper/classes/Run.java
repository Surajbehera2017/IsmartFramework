/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import atu.alm.wrapper.ITestCaseRun;
/*    */ import atu.alm.wrapper.enums.StatusAs;
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class Run
/*    */   implements ITestCaseRun
/*    */ {
/*    */   private Dispatch run;
/*    */   
/*    */   public Run(Dispatch run)
/*    */   {
/* 16 */     this.run = run;
/*    */   }
/*    */   
/*    */   public void setStatus(StatusAs as) {
/* 20 */     Dispatch.put(this.run, "Status", as.getStatus());
/*    */   }
/*    */   
/*    */   public void setName(String runName) {
/* 24 */     Dispatch.put(this.run, "Name", runName);
/*    */   }
/*    */   
/*    */   public void post() {
/* 28 */     Dispatch.call(this.run, "Post");
/*    */   }
/*    */   
/*    */   public int getID() {
/* 32 */     return Dispatch.call(this.run, "ID").getInt();
/*    */   }
/*    */   
/*    */   public StepFactory getStepFactory() {
/* 36 */     return new StepFactory(this.run);
/*    */   }
/*    */   
/*    */   public AttachmentFactory getAttachments()
/*    */   {
/* 41 */     return new AttachmentFactory(this.run);
/*    */   }

/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\Run.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */