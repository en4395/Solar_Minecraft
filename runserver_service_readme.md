# Autostart Guide

Author: Ella Noyes

This is how the mini PC (NUC) is able to run the minecraft server automatically on start. The steps followed are: 

1. In `usr/local/bin` directory, I created a file called `runserverscript.sh` containing:
```
#!/bin/bash
cd /home/pc/Forge_1.20.2_Server
./run.sh
exit 0
```

2. Made it executable using `chmod +x /usr/local/bin/runserverscript.sh`

3. Then `cd /etc/systemd/system/`

4. Make a file called `runserver.service` containing: 

```
[Unit]
Description=This service...
After=network.target

[Service]
Type=simple
ExecStart=/bin/bash /usr/local/bin/runserverscript.sh
TimeoutStartSec=0

[Install]
WantetBy=default.target
```

5. Run `systemctl daemon-reload` command to load the newly created service.

6. `systemctl enable runserver.service`

7. `systemctl start test.service`

How to stop the run server service:
`sudo systemctl stop runserver.service`


Source used: https://help.skysilk.com/support/solutions/articles/9000162390--basic-how-to-start-a-program-or-script-on-linux-automatically-on-boot-with-systemd
