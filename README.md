# Image-Viewer
A simple image viewer

It shows the images in a folder that the user selects...

I used the ImageIO method, read(BufferedImage), so it only supports formats: jpg, jpeg, bmp, gif (doesn't show animation) and wbmp.

It has some simple features like zoom, shuffle, slideshow with repeat and custom speed, list of the images in the folder, fit to screen and actual size and fullscreen view.

And there are some other features like:

1- User can open more than one folder (not at once). For each folder, an album with the name of the folder will be added to the left panel.

2- User can save the current folders. A file with the made-up type "igv" will be made in the folder that the user selects which will be holding the paths of the current added folders. Then the user can open the file using the "Open Album" button and all of the saved folders will be added to the left panel.
