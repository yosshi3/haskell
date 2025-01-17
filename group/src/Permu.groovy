
def Sn = {def out = []; (1..it as ArrayList).eachPermutation {out << it}; out}
println Sn(3)

List.metaClass.multiply = {delegate[it.collect{it - 1}]} //　＊演算定義、配列を使って入替
def (a,b) = [[1, 3, 2, 4], [4, 1, 2, 3]]
println "【${a} * ${b}】" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // 配列番号と配列数値が一致していない箇所を記録
	ret.size() == 2
} // 互換判定
println "【${a}.isTranspose()】" + a.isTranspose() + "  【${b}.isTranspose()】" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // 単位元を生成

ArrayList.metaClass.rep = {int tranA, int tranB ->
	use(Collections){delegate.swap(tranA, tranB)}
	delegate
} // 入替
println "【${a}.e().rep(0,1)】" + a.e().rep(0,1)

ArrayList.metaClass.transpose = {
	-> def (ret, obj) = [[], delegate.clone()]
	obj.eachWithIndex{it, i ->
		int ind = obj.findIndexOf{it == i + 1}
		if(i != ind){
			obj.rep(i,ind)
			ret.add(0, obj.e().rep(i,ind))
		}
	}
	ret
} // 互換リスト作成

ArrayList.metaClass.act = {delegate.inject{ret, val -> ret * val}} //　listlist内すべての要素に作用。畳み込み
println "【${b}.transpose()】" + b.transpose() + "  【${b}.transpose().act()】" + b.transpose().act()


ArrayList.metaClass.inv = {
	-> def ret = []; delegate.eachWithIndex{it, i -> ret << delegate.indexOf(i + 1) + 1}; ret} // 逆元
println "【${b}.inv()】" + b.inv() + "  【${b}.inv().inv()】" + b.inv().inv() + "  【[].inv()】" + [].inv()

ArrayList.metaClass.power = {
	def powerSet=[[]]
	delegate.each{Object obj ->
		powerSet.clone().each{ArrayList tmpA ->
			// ConcurrentModificationExceptionを避けるため、clone()
			ArrayList tmpAcopy = tmpA.clone()
			tmpAcopy.add(obj)
			powerSet.add(tmpAcopy)
		}
	}
	powerSet
} // 冪集合
println "【[1,2,3].power()】" + [1, 2, 3].power() + "  【${Sn(2)}.power()】" + Sn(2).power()

Boolean isSubset(ArrayList subset){
	if([0, 1].contains(subset.size())){return false} // 空集合、eは除く
	ArrayList twoElement = []
	ArrayList tmpsubset = subset.clone()
	tmpsubset.remove([])
	tmpsubset.each{def element1 ->
		tmpsubset.each{def element2 ->
			twoElement.add([element1.clone(), element2.clone()])
		}
	}
	ArrayList actedElement = []
	twoElement.each {actedElement << (it[0] * (it[1].inv()))} // a*b^(-1)の結果
	subset.containsAll(actedElement) // actedElementの結果がすべて含まれているか
}
ArrayList.metaClass.subset = {-> delegate.findAll {isSubset(it)}}
ArrayList.metaClass.disp = {-> delegate.eachWithIndex {it, i -> println "【${i}】${it}"}}

println "【[[], [[1, 2]], [[2, 1]], [[1, 2], [2, 1]]].subset()】" + [[], [[1, 2]], [[2, 1]], [[1, 2], [2, 1]]].subset()
Sn(3).power().subset().disp()
println Sn(5).size()

