/*    */ package atu.alm.wrapper.classes;
/*    */ 
/*    */ import com.jacob.com.Dispatch;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Attachment
/*    */ {
/*    */   private Dispatch currentAttachment;
/*    */   
/*    */   public Attachment(Dispatch currentAttachment)
/*    */   {
/* 12 */     this.currentAttachment = currentAttachment;
/*    */   }
/*    */   
/*    */   public void setDescription(String attachmentDescription)
/*    */   {
/* 17 */     Dispatch.put(this.currentAttachment, "Description", attachmentDescription);
/*    */   }
/*    */   
/*    */   public void setFileName(String filePath)
/*    */   {
/* 22 */     System.out.println("FILE PATH" + filePath);
/* 23 */     Dispatch.put(this.currentAttachment, "FileName", filePath);
/*    */   }
/*    */   
/*    */   public void setType(String type)
/*    */   {
/* 28 */     Dispatch.put(this.currentAttachment, "Type", type);
/*    */   }
/*    */   
/*    */   public void post() {
/* 32 */     Dispatch.call(this.currentAttachment, "Post");
/*    */   }
/*    */   
/*    */   public AttachmentStorage getAttachmentStorage() {
/* 36 */     return new AttachmentStorage(this.currentAttachment);
/*    */   }
/*    */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\Attachment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */