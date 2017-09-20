/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import com.jacob.com.Variant;
/*    */ 
/*    */ 
/*    */ public class AttachmentStorage
/*    */ {
/*    */   private Dispatch currentAttachment;
/*    */   private Dispatch attachmentStorage;
/*    */   
/*    */   public AttachmentStorage(Dispatch currentAttachment)
/*    */   {
/* 14 */     this.currentAttachment = currentAttachment;
/* 15 */     this.attachmentStorage = init();
/*    */   }
/*    */   
/*    */   private Dispatch init() {
/* 19 */     Dispatch attachmentStorage = Dispatch.call(this.currentAttachment, "AttachmentStorage").toDispatch();
/*    */     
/* 21 */     return attachmentStorage;
/*    */   }
/*    */   
/*    */   public void clientPath(String directoryPath) {
/* 25 */     Dispatch.call(this.attachmentStorage, "ClientPath");
/*    */   }
/*    */   
/*    */   public void save(String fileName) {
/* 29 */     Dispatch.call(this.attachmentStorage, "Save", new Object[] { fileName, Boolean.valueOf(true) });
/*    */   }
/*    */   
/*    */   public String getErrorMessage() {
/* 33 */     return Dispatch.call(this.attachmentStorage, "GetLastError").getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\AttachmentStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */