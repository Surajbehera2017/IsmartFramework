/*     */ package atu.alm.wrapper;
/*     */ 
/*     */ import atu.alm.wrapper.bean.ServerDetails;
/*     */ import atu.alm.wrapper.classes.Attachment;
/*     */ import atu.alm.wrapper.classes.AttachmentFactory;
/*     */ import atu.alm.wrapper.classes.AttachmentStorage;
/*     */ import atu.alm.wrapper.classes.BugFactory;
/*     */ import atu.alm.wrapper.classes.Run;
/*     */ import atu.alm.wrapper.classes.RunFactory;
/*     */ import atu.alm.wrapper.classes.Step;
/*     */ import atu.alm.wrapper.classes.StepFactory;
/*     */ import atu.alm.wrapper.classes.TDConnection;
/*     */ import atu.alm.wrapper.classes.TSTest;
/*     */ import atu.alm.wrapper.classes.TSTestFactory;
/*     */ import atu.alm.wrapper.classes.TestSet;
/*     */ import atu.alm.wrapper.classes.TestSetFolder;
/*     */ import atu.alm.wrapper.classes.TestSetTreeManager;
/*     */ import atu.alm.wrapper.collection.ListWrapper;
/*     */ import atu.alm.wrapper.enums.DefectPriority;
/*     */ import atu.alm.wrapper.enums.DefectSeverity;
/*     */ import atu.alm.wrapper.enums.DefectStatus;
/*     */ import atu.alm.wrapper.enums.StatusAs;
/*     */ import atu.alm.wrapper.exceptions.ALMServiceException;
/*     */ import com.jacob.activeX.ActiveXComponent;
/*     */ import com.jacob.com.ComFailException;
/*     */ import com.jacob.com.ComThread;
/*     */ import java.io.File;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ALMServiceWrapper
/*     */ {
/*  36 */   private ServerDetails serverDetails = null;
/*  37 */   private TDConnection almObj = null;
/*     */   
/*     */ 
/*     */   private String testSetFolder;
/*     */   
/*     */   private String testSetName;
/*     */   
/*     */ 
/*     */   public ALMServiceWrapper(String url)
/*     */   {
/*  47 */     this.serverDetails = new ServerDetails();
/*  48 */     this.serverDetails.setUrl(url);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean connect(String username, String password, String domain, String project)
/*     */     throws ALMServiceException
/*     */   {
/*  70 */     this.serverDetails.setUsername(username);
/*  71 */     this.serverDetails.setPassword(password);
/*  72 */     this.serverDetails.setDomain(domain);
/*  73 */     this.serverDetails.setProject(project);
/*  74 */     connectToOTA();
/*  75 */     return true;
/*     */   }
/*     */   
/*     */   private void releaseConnection() {
/*     */     try {
/*  80 */       if (getAlmObj().isConnected()) {
/*  81 */         getAlmObj().disconnect();
/*     */       }
/*  83 */       if (getAlmObj().isLoggedIn()) {
/*  84 */         getAlmObj().logout();
/*     */       }
/*  86 */       getAlmObj().releaseConnection();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   private void connectToOTA()
/*     */     throws ALMServiceException
/*     */   {
/*     */     try
/*     */     {
/*  97 */       ActiveXComponent activexObject = new ActiveXComponent("TDApiOle80.TDConnection");
/*     */       
/*  99 */       setAlmObj(new TDConnection(activexObject, getServerDetails()));
/* 100 */       releaseConnection();
/* 101 */       getAlmObj().initConnectionEx(getServerDetails().getUrl());
/* 102 */       getAlmObj().login(getServerDetails().getUsername(), getServerDetails().getPassword());
/*     */       
/* 104 */       getAlmObj().connect(getServerDetails().getDomain(), getServerDetails().getProject());
/*     */     }
/*     */     catch (UnsatisfiedLinkError e) {
/* 107 */       throw new ALMServiceException("Please add the jacob-(version-bit-type).dll file to the System path");
/*     */     }
/*     */     catch (ComFailException e) {
/* 110 */       throw new ALMServiceException("Please Register the OTAClient.dll with your system");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ //Arun
			public TestSetTreeManager Agettestfolder(){
				  TestSetTreeManager testSetTreeManager = getAlmObj().getTestSetTreeManager();
				return testSetTreeManager;
				
			}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ITestCase updateResult(String testSetFolderPath, String testSetName, int testSetID, String tcName, StatusAs as)
/*     */     throws ALMServiceException
/*     */   {
/* 129 */     TestSetTreeManager testSetTreeManager = getAlmObj().getTestSetTreeManager();
/*     */     
/* 131 */     TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);
/*     */     
/* 133 */     TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
/* 134 */     TSTestFactory tsTestFactory = testSet.getTSTestFactory();
/* 135 */     ListWrapper<TSTest> listWrapper = tsTestFactory.getNewList();
/* 136 */     for (TSTest tsTest : listWrapper) {
/* 137 */       if (tcName.equals(tsTest.getTestName())) {
/* 138 */         tsTest.putStatus(as);
/* 139 */         tsTest.post();
/* 140 */         return tsTest;
/*     */       }
/*     */     }
/* 143 */     throw new ALMServiceException("The Given Test Name \"" + tcName + "\" Not Found");
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
/*     */   public ITestSet getTestSet(String testSetFolderPath, String testSetName, int testSetID)
/*     */     throws ALMServiceException
/*     */   {
/* 158 */     TestSetTreeManager testSetTreeManager = getAlmObj().getTestSetTreeManager();
/*     */     
/* 160 */     TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);
/*     */     
/* 162 */     TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
/* 163 */     return testSet;
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
/*     */   public ITestCaseRun createNewRun(ITestCase tsTest, String runName, StatusAs as)
/*     */   {
/* 176 */     RunFactory runFactory = tsTest.getRunFactory();
/* 177 */     Run run = runFactory.addItem();
/* 178 */     run.setStatus(as);
/* 179 */     run.setName(runName);
/* 180 */     run.post();
/* 181 */     return run;
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
/*     */   public void addStep(ITestCaseRun run, String stepName, StatusAs as, String description, String expected, String actual)
/*     */   {
/* 196 */     StepFactory stepFactory = run.getStepFactory();
/* 197 */     Step step = stepFactory.addItem();
/* 198 */     step.setStepName(stepName);
/* 199 */     step.setStatus(as);
/* 200 */     step.setDescription(description);
/* 201 */     step.setExpected(expected);
/* 202 */     step.setActual(actual);
/* 203 */     step.post();
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
/*     */   public void newAttachment(String attachment, String description, HasAttachmentFeature hasAttachment)
/*     */     throws ALMServiceException
/*     */   {
/* 218 */     File file = new File(attachment);
/* 219 */     if (file.exists()) {
/* 220 */       Attachment attachmentFile = hasAttachment.getAttachments().addItem(file.getName());
/*     */       
/* 222 */       attachmentFile.setDescription(description);
/* 223 */       attachmentFile.post();
/* 224 */       AttachmentStorage as = attachmentFile.getAttachmentStorage();
/* 225 */       as.clientPath(file.getParent());
/* 226 */       as.save(file.getName());
/*     */     } else {
/* 228 */       throw new ALMServiceException("The Specified Attachment file does not exist");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IDefect newDefect()
/*     */   {
/* 240 */     IDefect defect = getAlmObj().getBugFactory().addItem();
/* 241 */     return defect;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int newDefect(String detectedBy, String assignedTo, DefectPriority priority, DefectSeverity severity, DefectStatus status, String summary, String detectedDate, String description, boolean isReproducible, String project, String attachment)
/*     */     throws ALMServiceException
/*     */   {
/* 266 */     IDefect bug = getAlmObj().getBugFactory().addItem();
/* 267 */     if ((detectedBy != null) && (detectedBy.length() > 0)) {
/* 268 */       bug.setDetectedBy(detectedBy);
/*     */     }
/* 270 */     if ((assignedTo != null) && (assignedTo.length() > 0)) {
/* 271 */       bug.setAssignedTo(assignedTo);
/*     */     }
/* 273 */     if (priority != null) {
/* 274 */       bug.setPriority(priority);
/*     */     }
/* 276 */     if (severity != null) {
/* 277 */       bug.setSeverity(severity);
/*     */     }
/* 279 */     if (status != null) {
/* 280 */       bug.setStatus(status);
/*     */     }
/* 282 */     if ((summary != null) && (summary.length() > 0)) {
/* 283 */       bug.setSummary(summary);
/*     */     }
/* 285 */     if ((detectedDate != null) && (detectedDate.length() > 0)) {
/* 286 */       bug.setDetectionDate(detectedDate);
/*     */     }
/*     */     
/* 289 */     if ((description != null) && (description.length() > 0)) {
/* 290 */       bug.setDescription(description);
/*     */     }
/* 292 */     bug.isReproducible(isReproducible);
/* 293 */     if ((project != null) && (project.length() > 0)) {
/* 294 */       bug.setProject(project);
/*     */     }
/*     */     
/* 297 */     File file = new File(attachment);
/* 298 */     if (file.exists()) {
/* 299 */       Attachment attachmentFile = bug.getAttachments().addItem(file.getName());
/*     */       
/* 301 */       attachmentFile.setDescription("Sample Attchment Desc");
/* 302 */       attachmentFile.post();
/* 303 */       AttachmentStorage as = attachmentFile.getAttachmentStorage();
/* 304 */       as.clientPath(file.getParent());
/* 305 */       as.save(file.getName());
/*     */     } else {
/* 307 */       throw new ALMServiceException("The Specified Attachment file does not exist");
/*     */     }
/*     */     
/*     */ 
/* 311 */     bug.save();
/* 312 */     return bug.getDefectID();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/* 321 */       releaseConnection(); return;
/*     */     }
/*     */     catch (Exception e) {}finally
/*     */     {
/*     */       try {
/* 326 */         ComThread.Release();
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ServerDetails getServerDetails()
/*     */   {
/* 337 */     return this.serverDetails;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TDConnection getAlmObj()
/*     */   {
/* 344 */     return this.almObj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAlmObj(TDConnection almObj)
/*     */   {
/* 352 */     this.almObj = almObj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getTestSetFolder()
/*     */   {
/* 359 */     return this.testSetFolder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTestSetFolder(String testSetFolder)
/*     */   {
/* 367 */     this.testSetFolder = testSetFolder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getTestSetName()
/*     */   {
/* 374 */     return this.testSetName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTestSetName(String testSetName)
/*     */   {
/* 382 */     this.testSetName = testSetName;
/*     */   }
/*     */ }


/* Location:              C:\Users\shanmar\Desktop\com4j-otaclient-wrapper-master\ATU_ALM_ServiceWrapper_2.0.jar!\atu\alm\wrapper\ALMServiceWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */