---
layout: page
title: Shao Feng's Project Portfolio Page
---

### Project: Medimate

MediMate (MM) is a cross-platform desktop application for medical professionals, specifically for private doctors or their receptionists, who are experienced with computers and currently using paper records to store patient information. With this solution, they will be able to better manage their patient data, including updating, accessing and adding new patient details easily. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MM can get your contact management tasks done faster than traditional GUI apps.


Given below are my contributions to the project.

* **New Feature**: Delete multiple patient records at once
    * What it does: This new feature allows users to delete multiple patient records at once from the MediMate application. The delete command takes one or more arguments as input, which specify the index of the patient records to be deleted.
    * Justification: This feature is important because it provides a convenient way for users to delete multiple patient records quickly.
    * Highlights: The delete command can be used to delete patient records at the specified index. Multiple patient records can be selected for deletion by specifying multiple index arguments separated by spaces. The order of the INDEX arguments does not need to be sequential or follow a specific numerical pattern. The delete command can also be used with the find command to delete specific patient records from a search result.

* **New Feature**: Medical File Upload for Patients
    * What is does: The "Upload file" feature allows users to upload files, such as images, documents, or videos, to the MediMate application. The user can navigate and select a file to be uploaded, which is then stored securely in the application.
    * Justification: The ability to upload files is essential for healthcare providers who need to maintain patient records, share test results, or collaborate on treatment plans. It can help to streamline communication between healthcare providers and ensure that patient information is easily accessible.
    * Highlights: The "Upload file" feature supports a variety of file types and formats, including PDF, JPG, and PNG.
      The uploaded files are securely stored in the application, preventing unauthorized access or data loss.
      The "Upload file" feature can be easily integrated into the existing patient record system.
      The implementation of this feature required careful consideration of security and storage considerations, and the use of third-party libraries may be required for certain file types.

* **New Feature**: Create PDF Medical Certificate
    * What is does: The "Create PDF Medical Certificate" feature allows users to generate medical certificates for patients in the MediMate application. The user specifies the patient's index, doctor's name, medical condition, and number of days of unfitness, and a PDF certificate is generated for the patient.
    * Justification: Medical certificates are an important part of patient care, as they document the patient's medical condition and treatment plan. A feature that automates the creation of these certificates saves healthcare providers time and ensures that the certificates are accurate and consistent.
    * Highlights:The create command takes several arguments, including the index of the patient, doctor's name, medical condition, and number of days of unfitness.
      The generated certificate is in PDF format, which can be easily shared and printed.
      The feature can be easily integrated into the existing patient record system.
      The implementation of this feature requires careful consideration of the layout and content of the medical certificate, as well as the security of the generated PDF files. 
    * Credits: *{The implementation of the "Create PDF Medical Certificate" feature may require the use of third-party libraries, such as PDFBox, for generating the PDF certificates. PDFBox is an open-source Java library for working with PDF documents. Its use in the implementation of this feature can significantly reduce development time and effort.}*

* **New Feature**: View Medical Files and PDF Certificates
    * What is does: The "View" feature allows users to view files in the application. The user can select a file to view, and the file will open in a new window, allowing the user to read, review, or edit the contents of the file.
    * Justification: The ability to view medical files and PDF certificates is essential for healthcare providers who need to access patient information quickly and efficiently. This feature can help to streamline communication between healthcare providers and ensure that patient information is easily accessible.
    * Highlights: The "View" feature supports both medical files and PDF certificates, making it a versatile tool for healthcare providers.
      The user can specify the FILE INDEX argument to view a specific file for a patient with multiple files.
      The implementation of this feature requires careful consideration of security and access control, as patient information is sensitive and must be protected from unauthorized access.
    * Credits: *{The implementation of the "View" feature may require the use of third-party libraries, such as PDFBox, for viewing PDF certificates. PDFBox is an open-source Java library for working with PDF documents. Its use in the implementation of this feature can significantly reduce development time and effort. The implementation may also require the use of other libraries for working with medical files and data, depending on the specific requirements of the application.}*
  


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=SFCoding123&zR=AY2223S2-CS2103T-W11-4%2Ftp%5Bmaster%5D&zACS=252.11904274902844&zS=2023-02-17&zFS=&zU=2023-04-03&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (5 releases) on GitHub

* **Enhancements to existing features**:


* **Documentation**:
    * User Guide:
        * Added documentation for the features `deletes` and `upload` [\#72](https://github.com/AY2223S2-CS2103T-W11-4/tp/commit/3370f4fa252d97600ae222490f483039d41a1510)
    * Developer Guide:
        * Added Design details of the `Architecture` and `Files Component` .[\#260](https://github.com/AY2223S2-CS2103T-W11-4/tp/commit/6b84c873050104b24539f616dcdc77bb7f16aa10)

* **Tools**:
    * Integrated a third party library PDFBOX to the project ([\#227](https://github.com/AY2223S2-CS2103T-W11-4/tp/commit/e6d846c1955727f8576777417bb2de4f3cbc65a8))
    * IntelliJ IDEA CE
    * Source Tree
    * Plant UML
