Set WshShell = CreateObject("Wscript.Shell")
WshShell.currentdirectory="..\report\android\junittestcase\android"
WshShell.Run "cmd /c dir /s/b *.html > list.tmp",vbHide
Wscript.Sleep 1000

sFile = "list.tmp"
Set objFSO = CreateObject("Scripting.FileSystemObject")
Set oFile = objFSO.OpenTextFile(sFile,1)
Do While Not oFile.AtEndOfStream
      strLine = oFile.ReadLine
      If Len(strLine) > 0 Then
            Set File = objFSO.OpenTextFile(strLine, 1)
            aryLines = File.ReadAll
            File.Close
            aryLines = Replace(aryLines, "ltltlt", "<")
			aryLines = Replace(aryLines, "gtgtgt", ">")
            Set File = objFSO.OpenTextFile(strLine, 2)
            File.Write aryLines
            File.Close
      End If
Loop
oFile.Close

objFSO.DeleteFile sFile
Set objFSO = Nothing