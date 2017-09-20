/*     */ package atu.alm.wrapper.classes;
/*     */ 
/*     */ import atu.alm.wrapper.bean.ServerDetails;
/*     */ import atu.alm.wrapper.exceptions.ALMServiceException;
/*     */ import com.jacob.activeX.ActiveXComponent;
/*     */ import com.jacob.com.ComFailException;
/*     */ import com.jacob.com.Dispatch;
/*     */ import com.jacob.com.Variant;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TDConnection
/*     */ {
/*  17 */   private ActiveXComponent almObject = null;
/*     */   
/*     */   public TDConnection(ActiveXComponent almObject, ServerDetails serverDetails) {
/*  20 */     setAlmObject(almObject);
/*     */   }
/*     */   
/*     */   public BugFactory getBugFactory() {
/*  24 */     return new BugFactory(getAlmObject());
/*     */   }
/*     */   
/*     */   public TestFactory getTestFactory() {
/*  28 */     return new TestFactory(getAlmObject());
/*     */   }
/*     */   
/*     */   public TreeManager getTreeManager() {
/*  32 */     return new TreeManager(getAlmObject());
/*     */   }
/*     */   
/*     */   public TestSetTreeManager getTestSetTreeManager() {
/*  36 */     return new TestSetTreeManager(getAlmObject());
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean initConnectionEx(String url)
/*     */     throws ALMServiceException
/*     */   {
/*     */     try
/*     */     {
/*  45 */       Dispatch.call(getAlmObject(), "InitConnectionEx", new Object[] { url });
/*  46 */       return true;
/*     */     } catch (ComFailException e) {
/*  48 */       throw new ALMServiceException("Unable to Establish connection for the Given URL: " + url);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean login(String username, String password)
/*     */     throws ALMServiceException
/*     */   {
	
/*     */     try
/*     */     {
	
/*  64 */       Dispatch.call(getAlmObject(), "login", new Object[] { username, password });
/*  65 */       return true;

/*     */     } catch (ComFailException e) {
	
/*  67 */       throw new ALMServiceException("Invalid Username or Password");

/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean connect(String domain, String project)
/*     */     throws ALMServiceException
/*     */   {
/*     */     try
/*     */     {
/*  78 */       Dispatch.call(getAlmObject(), "connect", new Object[] { domain, project });
/*  79 */       return true;
/*     */     } catch (ComFailException e) {
/*  81 */       throw new ALMServiceException("Invalid Project or Domain");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/*  99 */     boolean isLoggedIn = false;
/*     */     try {
/* 101 */       isLoggedIn = Dispatch.call(getAlmObject(), "Connected").getBoolean();
/*     */     }
/*     */     catch (IllegalStateException e) {
/* 104 */       return isLoggedIn;
/*     */     }
/* 106 */     return isLoggedIn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLoggedIn()
/*     */   {
/* 117 */     boolean isLoggedIn = false;
/*     */     try {
/* 119 */       isLoggedIn = Dispatch.call(getAlmObject(), "loggedIn").getBoolean();
/*     */     } catch (IllegalStateException e) {
/* 121 */       return isLoggedIn;
/*     */     }
/* 123 */     return isLoggedIn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean disconnect()
/*     */   {
/* 135 */     Dispatch.call(getAlmObject(), "disconnectProject");
/* 136 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean logout()
/*     */   {
/* 145 */     Dispatch.call(getAlmObject(), "logout");
/* 146 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean releaseConnection()
/*     */   {
/* 155 */     Dispatch.call(getAlmObject(), "releaseConnection");
/* 156 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ActiveXComponent getAlmObject()
/*     */   {
/* 163 */     return this.almObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAlmObject(ActiveXComponent almObject)
/*     */   {
/* 171 */     this.almObject = almObject;
/*     */   }
/*     */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\classes\TDConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */