package com.assistmicro.groovy.SampleDocumentGenerator


import java.awt.TexturePaintContext.Int;
import java.lang.StringBuilder

public class SampleDocumentGeneratorMain {

	//フィールド設定

	//生成ファイル数上限 デフォルトは10万
	private Long maxTotalFileCount =2000
	//最大階層数
	private Long directoryHierarchyDepth=5
	//ファイル/フォルダ番号共通カウンタ
	private Long fileDirNoConter = 1
	//マスタサンプルデータ
	private String sampleFile="sample.txt"
	//1フォルダに生成するファイル数の上限 デフォルトは100
	private Long filesToBeCreate = 100
	//1フォルダに生成するフォルダ数の上限
	private Long directoriesToBeCreate=100
	//生成するファイルの長さ設定デフォルトは50KB
	private Long targetfileLength=51200
	//ファイルデリミタ
	private String FS = File.separator
	//改行文字
	private String BR = System.getProperty("line.separator")
	//サンプルファイル読み込み用クラス
	private SampleReader sr
	private SearchIdentiferReader searchIdReader

	private Long createdFileCount = 0

	//ファイル生成のコンストラクタ
	public SampleDocumentGeneratorMain() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	//SampleDataセット
	public setBaseSampleData(String sampleDataPath){
		sr = new SampleReader(sampleDataPath)
	}
	//検索キー取得クラスセット
	public setSearchIdData(String FilePath){
		searchIdReader = new SearchIdentiferReader(FilePath)
	}
	
	//ディレクトリ構造体とファイルの一括生成
	public Void generateSampleStructure( String directoryPath, Long depth=1){
		BigDecimal fileCount=0
		BigDecimal dirCount=0
		++depth
		//当ディレクトリにサンプルファイル作成
		String searchID = searchIdReader.getNextIdentifer()
		for (Long i=1;i <=filesToBeCreate;i++){
			//予定最大数だけ作ったらもう作らない
			if (getCreatedFileCount()>=maxTotalFileCount){
				break
			}
			generateTextFile(directoryPath,searchID)
		}
		//サブディレクトリ作成
		if (getCreatedFileCount()>=maxTotalFileCount){
			//ファイル最大作成済みなら処理終了
			return
		} else {
			if  (depth >= directoryHierarchyDepth)  {
				//println "reached max depth"
				return
			} else {
				while (dirCount <=directoriesToBeCreate){
					//サブディレクトリの追加
					String subDirectory = directoryPath + FS + this.getNewSubDirectoryName()
					File fl = new File(subDirectory)
					fl.mkdirs()
					println "directory was created: " + subDirectory
					fl = null
					dirCount++
					//recursive call
					generateSampleStructure(subDirectory,depth)
				}
			}
		}
	}

	//ファイル生成(生成するデータの末尾に任意の文字列を追加可能)
	public generateTextFile(String directoryPath, String searchKey=""){
		StringBuilder sb = new StringBuilder()
		BufferedWriter bw
		//指定の個数を生成するまでループ
		//サンプルファイルからデータを読み込んで新しいファイルへ書込む
		String filname = getNewFileName()
		String lineData
		bw = new BufferedWriter(new FileWriter(new File(directoryPath + FS + filname)))
		Long fileLength=0
		for(;;){
			//マスターデータからサンプルデータ読み出し
			lineData = sr.getNewLineData()
			fileLength = fileLength + lineData.getBytes().length
			sb.append(lineData.toString() + BR) //行末に改行追加
			//データ長チェック処理
			if(fileLength >=targetfileLength) {
				if (searchKey.length() > 0) sb.append(searchKey)
				break
			}
		}
		bw.write(sb.toString())
		bw.flush()
		bw.close()
		sb.setLength(0)
		addFileCount()
		println "file was created: " + directoryPath + FS + filname
	}
	private String getNewFileName(){
		//最新のシリアル番号からファイル名を生成
		return getFileDirSerialNo().toString() + ".txt"
	}
	private String getNewSubDirectoryName(){
		//最新のシリアル番号を取得
		getFileDirSerialNo().toString()
	}
	//ファイルとフォルダ共通のシリアル番号を返却する
	private Long getFileDirSerialNo(){
		fileDirNoConter++
	}
	//終了判定処理
	private boolean isTaskCompleted(){
	}

	//生成済みファイルカウンタ
	private addFileCount(){
		createdFileCount++
	}
	private Long getCreatedFileCount(){
		return createdFileCount
	}
}

