
--------------------------------    To run a Script on Grid   --------------------------------

1) When both the Hub and Node works from my computer(local machine)
    -- Have to include my ip address(ipv4 for wi-fi since I'm using wi-fi connection) as part of the json file (myHub.json)
    -- Then need to run the batch file (myHub-start.bat) from the command prompt
    -- Again need to include my own ip address for the myNode.json file
    -- Open another command prompt and run the myNode-start.json file
    -- Need to keep both the command prompts open as long as I want to use the grid

2) When the Node works from my computer(local machine) and the Hub works from the Shift's server (or, any remote server)
    -- Have to include Shift's ip address as part of the json file (hub.json)
    -- Then need to run the batch file (hub-start.bat) from the command prompt.
    -- Then I need to include my own ip address for the node.json file
    -- Open another command prompt and run the node-start.json file
    -- Need to keep both the command prompts open as long as I want to use the grid