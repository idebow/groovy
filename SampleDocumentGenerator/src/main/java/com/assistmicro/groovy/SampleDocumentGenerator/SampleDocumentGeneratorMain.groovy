package com.assistmicro.groovy.SampleDocumentGenerator


import java.awt.TexturePaintContext.Int;
import java.lang.StringBuilder

public class SampleDocumentGeneratorMain {

	//デバッグフラグ
	private boolean debug=false
	//標準出力に情報を出す件数
	private Long indicater = 10000
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
	private Long filesParDirectory = 100
	//1フォルダに生成するフォルダ数の上限
	private Long directoriesParDirectory=100
	//生成するファイルの長さ設定デフォルトは50KB
	private Long targetfileLength=51200
	//ファイルデリミタ
	private String FS = File.separator
	//改行文字
	private String BR = System.getProperty("line.separator")
	//書き出し文字コード
	private String encoding ="UTF-8"
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
	public void generateSampleStructure( String directoryPath, Long depth=1){
		BigDecimal fileCount=0
		BigDecimal dirCount=0
		++depth
		//当ディレクトリにサンプルファイル作成
		String searchID = searchIdReader.getNextIdentifer()
		for (Long i=1;i <=filesParDirectory;i++){
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
				if (debug) println "reached max depth"
				return
			} else {
				while (dirCount <=directoriesParDirectory){
					//サブディレクトリの追加
					String subDirectory = directoryPath + FS + this.getNewSubDirectoryName()
					File fl = new File(subDirectory)
					fl.mkdirs()
					if (debug) println "directory was created: " + subDirectory
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
		bw = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(directoryPath + FS + filname), getOutFileEncoding()))
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
		if (debug) println "file was created: " + directoryPath + FS + filname
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
	//生成済みファイルカウンタ
	private addFileCount(){
		createdFileCount++
		if ((createdFileCount % indicater) ==0){
			println "[" + getSystemDateString() + "] " + createdFileCount.toString() +"ファイルの作成が完了しました。"
		}
	}
	private Long getCreatedFileCount(){
		return createdFileCount
	}


		//出力ファイルエンコーディング設定
	public setOutFileEncoding(String encoding){
		this.encoding = encoding
	}
	public String getOutFileEncoding(){
		this.encoding
	}
	//トータル作成ファイルの上限
	public setMaxCreateFileNumber(Long cont){
		this.maxTotalFileCount = cont
	}
	//ディレクトリ構造の最大深度
	public setDirectoryHierarchyDepth(Long maxDepth){
		this.directoryHierarchyDepth = maxDepth
	}
	//1フォルダに作成する最大ファイル数
	public setFilesParDirectory(Long fileCount){
		this.filesParDirectory = fileCount
	}
	//1フォルダに作成する最大ディレクトリ数
	public setDirectoriesParDirectory(Long dirCount){
		this.directoriesParDirectory=dirCount
	}
	//作成するファイルの長さ
	public setOutFileLength(Long fileLength){
		this.targetfileLength = fileLength
	}

	//日付をとる
	public String getSystemDateString(){
		return (new Date(System.currentTimeMillis())).format("yyyy/MM/dd HH:MM:ss")
	}
	
}

