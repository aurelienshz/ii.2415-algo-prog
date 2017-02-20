filename=index

pdf:
	pdflatex -output-directory=target src/${filename}.tex

launch: pdf read

pdf-print: ps
	ps2pdf -dColorConversionStrategy=/LeaveColorUnchanged -dPDFSETTINGS=/printer ${filename}.ps

text: html
	html2text -width 100 -style pretty ${filename}/${filename}.html | sed -n '/./,$$p' | head -n-2 >${filename}.txt

html:
	@#latex2html -split +0 -info "" -no_navigation ${filename}
	htlatex ${filename}

ps:	dvi
	dvips -t letter ${filename}.dvi

dvi:
	latex ${filename}
	bibtex ${filename}||true
	latex ${filename}
	latex ${filename}

read:
	evince target/${filename}.pdf &

.PHONY: clean
clean:
	rm -f target/${filename}.ps target/${filename}.pdf target/${filename}.log target/${filename}.aux target/${filename}.out target/${filename}.dvi target/${filename}.bbl target/${filename}.blg target/*.log target/*.toc
