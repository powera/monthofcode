# monthofcode

In February 2007, I did a "month of code" project where I wrote a small self-contained program every day.

8 years later, I'm setting this up on a proper source code repository.
It's kind of interesting how some things no longer work at all (Java applets), others
are mostly broken (anything involving databases), while a few still work perfectly
(the 75-line C program to make Mandlebrot images).  The choice of PHP instead of Python
looks especially strange 8 years out.

February 3 - Addition Applet - Uses only JavaScript - Enter a level. It randomly generates an addition problem with numbers up to around the square root of the level (with scaling at lower levels). When you get a problem right, your level goes up by the correct sum. It tracks your current session's percentage. If you refresh the page, your stats will be cleared, but it will auto-populate the level.

February 4 - Color Picker - Uses JavaScript to update the image, and PHP to generate the image with the desired colors - Enter a hex-color code for the background, text, and field. A "Flag" designed in honor of the Super Bowl, with a football field, endposts, and the words "Colts" and "Bears" will be automatically updated with your color selections.

February 5 - Cryptogram Tools - Uses PERL for the encryption, and PHP for the decryption tools. Uses Javascript for some minor tools. - The "Encrypt" page takes as argument a plaintext and a 26-character cipher to user for the encryption. This can be generated randomly by Javascript. Pressing the "Encode" button will translate the text, retaining cases, and leaving any punctuation, numbers, etc. unchanged. The "Decoding Tools" page takes a cryptogram as argument. It will display a letter distribution for the page text, along with a list of one to four letter words in the text sample. Some useful solving tips are included as well.

February 6 - Hunt the Wumble - Uses PHP and AJAX - After entering your name, you are placed in the dungeon of the wumble, with two rounds of ammunition. You must traverse the dungeon and attempt to shoot the mysterious wumble. Don't walk into its lair or the wumble will flee the dungeon! (Do not confuse this with the similar Hunt the Wumpus game.)

February 7 - Mandelbrot Viewer - Uses PHP, Perl, and C to create images (with GD), PHP for navigation pages - There are three separate pages which can create images of the Mandelbrot set. As performance is an order of magnitude better using C, the zoomable version of the Mandelbrot set is written in C. It loops colors after 48 iterations, and you can change the size of the image or increase the iteration count. You can also click on the image to zoom in on that point.

February 8 - 3D Tic-Tac-Toe - Uses Java - Click on the square to play there. Four in a row wins, in any direction. There is a basic AI which will be tough for a beginner to beat. When there is a winner, the completed squares will be highlighted.

February 9 - Word Problems - Uses AJAX and PHP back-end - Automatically generates one of 6 different types of word problems.

February 10 - Compound Interest Calculator - Uses only Javascript - Calculates amount based on principal, interest rate, and time period.

February 11 - Flash Card - Uses PHP and mySQL - Select one of the three flash card sets. A random entry is selected and one of the fields designated as "unique" for that set will display. (For example, for a math problem, the problem is unique but the answer is not. For Chinese-English translation, both languages would be unique.). Clicking "View Solution" will display all the other information associated with the card. You can also select the level for the problem you want. New data sets or entries can be added relatively easily with only mySQL queries. A tool to add words to the Chinese Vocabulary dataset is linked.

February 12 - Word Find Generator - Uses Java - Creates a 16x16 wordfind using up to 25 words of 5 letters or greater. Words are from a corpus of over 500 words. To select a word, click on the first letter of the word, and drag to the last letter of the word. Click "New Game" to generate a new board, or "Show Solution" to view the location of all the words.

February 13 - Numerical Evaluator - Uses PERL - Parses a mathematical expression containing numerical values, +-*/, and parentheses. Displays the steps taken in order to evaluate the expression, and prints the result. 

February 14 - "Java World" - Uses Java - Block world simulator similar to the famous SHRDLU, but with more limited abilities. You can add, move, and remove objects, as well as ask some basic questions about the state of the world. Known Bug - Large objects are larger than very large objects. 

February 15 - Neural Networker - Uses C - The C program can either construct a random neural network of arbitrary size or load one from a file, and conduct learning on that. This is implemented with a web interface on the specific function (x*Input1+y*Input2+1)/(2+x+y), with X and Y user inputs. The program will do up to 25000 training cycles with randomly generated inputs, with a "score" being displayed at different levels. 

February 16 - Space Wars 5959 - Uses Java - A Space Invaders-type game. Shoot the ships without getting hit yourself. Try to beat your previous high score. Use left-mouse button to fire or right-mouse button to toggle auto-fire.

February 17 - Stock Price Tools - Uses PHP and mySQL - Displays a chart with the stock prices for any S&P 500 stock from Dec 23, 2005 to Feb 16, 2007.

February 18 - Web Board - Uses PHP/mySQL - You can enter your name, a comment title, and a comment. Functions more like a guest book, as there is no capacity for threaded comments. You can go back and forward to view 20 entries at a time, or link to a single entry. Also includes an RSS feed of the information.

February 19 - Metal Wars - Uses PHP and AJAX - Basically like Dope Wars, only with metals instead of drugs. Try to beat $1000000 in just 30 days.

February 20 - CAPTCHA Generator - Uses PHP - Type your text into the box, and a CAPTCHA based on that text will be generated. The CAPTCHA uses several anti-OCR techniques including variable placement of characters, variable color of characters, random rows of pixels being shifted horizontally, random rows of pixels being inverted, and random rows of pixels being erased.

February 21 - "Java Town" - Uses Java - Applet of a car driving down a road in mock-3D. The houses and cross street will randomly move each time the car reaches the end of the road, to demonstrate the use of perspective. Unfortunately, I couldn't figure out anything for you to do in this one other than watch.

February 22 - Beat the Clock - Uses Javascript and PHP - Pick a board size, and hit start. Click on the numbers in order starting with 1 as fast as you can. Try to beat the goal times.

February 23 - Vignere Cypher Generator - Uses PERL - Encrypts a text using a given cipher.

February 24 - Card Game - Uses Java - A random hand is dealt. Click on the cards to select them for replacement, or deal a fresh hand from a new deck.

February 25 - Unit Converter - Uses JavaScript - Converts between various units of length, volume, and mass.

February 26 - Random Data Generator - Uses PHP - Generates a CSV list containing random integers, decimals, or character strings of type selected by the user.

February 27 - John Conway's Game of Life - Uses JavaScript - Does the game of life simulation. You can check or uncheck boxes manually. On iteration, a new cell will be born if it is adjacent vertically, horizontally, or diagonally to exactly 3 cells, and an existing cell will survive if it is adjacent to 2 cells.

February 28 - Town Model - Uses Java - An arbitrary number of points (set to 3) move around the area. The position they are attracted to is dependent on their location, and they move randomly towards that location. Unfortunately, it is not particularly configurable.
