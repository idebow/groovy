package com.assistmicro.groovy.SampleDocumentGenerator

import java.awt.geom.Path2D.Iterator;

//サンプルファイル読み込みようクラス
class SampleReader {
	private String sampleFilePath
	private BufferedReader br
	private List<Long> shiftCounterSteps = [
		1,
		2,
		3,
		4,
		5,
		6,
		7,
		8,
		9,
		10,
		11,
		12,
		13,
		14,
		15,
		16,
		17,
		18,
		19,
		20
	]
	ListIterator<Long> shiftCounterStepsItarator

	private String encoding ="UTF-8"

	public SampleReader(String sampleFilePath, String encoding) {
		// TODO 自動生成されたコンストラクター・スタブ
		shufleShiftLineCounter()
		setSampleFilePath(sampleFilePath)
		setEncoding(encoding)
		reopen()
	}
	//ファイルを開く(開き直す)
	public reopen(){
		if (br != null) br.close()
		br = new BufferedReader(new InputStreamReader(new  FileInputStream ( getSampleFilePath()),getEncoding()))
	}
	//一行文の文字列データを返却する
	public String getNewLineData(){
		Long shiftCount = getShiftLineCount()
		for(Long i=1; i < shiftCount;i++){
			br.readLine()
		}
		String lineData = br.readLine()
		if (lineData ==null) {
			this.reopen()
			lineData = br.readLine()
		}
		return lineData
	}

	//スキップする行数を決定する
	public shufleShiftLineCounter(){
		Collections.shuffle(shiftCounterSteps)
		shiftCounterStepsItarator = shiftCounterSteps.listIterator()
	}
	private Long getShiftLineCount(){
		if (!shiftCounterStepsItarator.hasNext()) {
			//コレクションの最後までいったら再度シャッフル
			shufleShiftLineCounter()
		}
		shiftCounterStepsItarator.next()
	}

	//サンプルファイルのパスを格納する
	public setSampleFilePath(String path){
		sampleFilePath = path
	}
	public String getSampleFilePath(){
		sampleFilePath
	}

	public setEncoding(String encoding){
		this.encoding =encoding
	}
	
	public String getEncoding(){
		this.encoding
	}

}
