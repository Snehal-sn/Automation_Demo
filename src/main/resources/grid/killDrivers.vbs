strComputer = "."
Set objWMIService = GetObject("winmgmts:" & "{impersonationLevel = impersonate}!\\" & strComputer & "\root\cimv2")


'Close Chrome driver
Set colProcessList = objWMIService.ExecQuery _
	("SELECT * FROM Win32_Process WHERE Name = 'chromedriver.exe'")
If colProcessList.Count > 0 Then
	For Each objProcess in colProcessList
		objProcess.Terminate()
	Next
End If


'Close Firefox driver
Set colProcessList = objWMIService.ExecQuery _
	("SELECT * FROM Win32_Process WHERE Name = 'geckodriver.exe'")
If colProcessList.Count > 0 Then
	For Each objProcess in colProcessList
		objProcess.Terminate()
	Next
End If


'Close IE driver
Set colProcessList = objWMIService.ExecQuery _
	("SELECT * FROM Win32_Process WHERE Name = 'IEDriverServer.exe'")
If colProcessList.Count > 0 Then
	For Each objProcess in colProcessList
		objProcess.Terminate()
	Next
End If