package com.assistmicro.groovy.SampleDocumentGenerator

import com.assistmicro.groovy.SampleDocumentGenerator.SampleDocumentGeneratorMain;

SampleDocumentGeneratorMain s = new SampleDocumentGeneratorMain()

//ここにベースとするデータのファイルをセット(Windowsの場合は区切り文字をエスケープする"\\")
//s.setBaseSampleData("C:\\WORK\\testdata\\青空\\text\\01_hangan_chimatao_iku.txt")
s.setBaseSampleData("C:\\WORK\\testdata\\青空\\aozora_all.txt")
//s.generateSampleStructure("/Users/999/Documents/home")
s.generateSampleStructure("C:\\WORK\\testdata\\files")

