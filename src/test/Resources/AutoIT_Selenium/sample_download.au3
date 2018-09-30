#include <MsgBoxConstants.au3>
#include <WinAPIFiles.au3>

; Download a file in the background.
; Wait for the download to complete.

Example()

Func Example()
    ; Save the downloaded file to the temporary folder.
    Local $sFilePath = C:\Users\DEBAJYOTI007\IdeaProjects\SpreeTestPractice\src\test\Resources\AutoIT_Download\IEDriver64.zip

    ; Download the file in the background with the selected option of 'force a reload from the remote site.'
    Local $hDownload = InetGet("https://goo.gl/ns6MEj", $sFilePath, $INET_FORCERELOAD, $INET_DOWNLOADBACKGROUND)

EndFunc   ;==>Example

