/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class AttachmentFactory
/*    */ {
/*    */   private Dispatch attachmentFactory;
/*    */   private Dispatch bug;
/*    */   
/*    */   public AttachmentFactory(Dispatch bug)
/*    */   {
/* 14 */     this.bug = bug;
/* 15 */     this.attachmentFactory = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 19 */     Dispatch attachmentFactory = Dispatch.call(this.bug, "Attachments").toDispatch();
/*    */     
/* 21 */     return attachmentFactory;
/*    */   }
/*    */   
/*    */   public Attachment addItem(String fileName) {
/* 25 */     Dispatch attachment = Dispatch.call(this.attachmentFactory, "AddItem", new Object[] { fileName }).toDispatch();
/*    */     
/* 27 */     return new Attachment(attachment);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\AttachmentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */