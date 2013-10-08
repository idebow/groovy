package com.assistmicro.groovy.SampleDocumentGenerator

import java.awt.geom.Path2D.Iterator;

//検索キー読み込みようクラス
class SearchIdentiferReader {
	private String identiferFilePath
	private BufferedReader br

	public SearchIdentiferReader(String FilePath) {
		// TODO 自動生成されたコンストラクター・スタブ
		setIdentiferFilePath(FilePath)
		reopen()
	}
	//ファイルを開く(開き直す)
	public reopen(){
		if (br != null) br.close()
		br = new BufferedReader(new InputStreamReader(new  FileInputStream ( getIdentiferFilePath()),"UTF-8"))
	}

	//一行文の文字列データを返却する
	public String getNextIdentifer(){
		String lineData = br.readLine()
		if (lineData==null) {
			this.reopen()
			lineData = br.readLine()
		}
		return lineData
	}

	//サンプルファイルのパスを格納する
	public setIdentiferFilePath(String path){
		identiferFilePath = path
	}
	public String getIdentiferFilePath(){
		identiferFilePath
	}

}
