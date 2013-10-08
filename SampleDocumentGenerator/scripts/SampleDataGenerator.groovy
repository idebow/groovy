package com.assistmicro.groovy.SampleDocumentGenerator

import com.assistmicro.groovy.SampleDocumentGenerator.SampleDocumentGeneratorMain;

SampleDocumentGeneratorMain s = new SampleDocumentGeneratorMain()

//パス指定時の注意 Windows系は\\
//ここにベースとするデータのファイルをセット
//s.setBaseSampleData("C:\\WORK\\testdata\\青空\\text\\01_hangan_chimatao_iku.txt")
s.setBaseSampleData("C:\\WORK\\testdata\\青空\\aozora_all.txt")
//ここに検索のキーにするデータを格納したファイルのパスを設定する
s.setSearchIdData("C:\\WORK\\testdata\\names_200k.txt")
//サンプルデータを出力するディレクトリのパスを指定して処理を開始する
//s.generateSampleStructure("/Users/999/Documents/home")
s.generateSampleStructure("C:\\WORK\\testdata\\files")

