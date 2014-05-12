
def Sn = {def out = []; (1..it as ArrayList).eachPermutation {out << it}; out}
println Sn(3)

List.metaClass.multiply = {delegate[it.collect{it - 1}]} //Å@ÅñââéZíËã`ÅAîzóÒÇégÇ¡Çƒì¸ë÷
def (a,b) = [[1, 3, 2, 4], [4, 1, 2, 3]]
println "Åy${a} * ${b}Åz" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // îzóÒî‘çÜÇ∆îzóÒêîílÇ™àÍívÇµÇƒÇ¢Ç»Ç¢â”èäÇãLò^
	ret.size() == 2
} // å›ä∑îªíË
println "Åy${a}.isTranspose()Åz" + a.isTranspose() + "  Åy${b}.isTranspose()Åz" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // íPà å≥Çê∂ê¨

ArrayList.metaClass.rep = {int tranA, int tranB ->
	use(Collections){delegate.swap(tranA, tranB)}
	delegate
} // ì¸ë÷
println "Åy${a}.e().rep(0,1)Åz" + a.e().rep(0,1)

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
} // å›ä∑ÉäÉXÉgçÏê¨

ArrayList.metaClass.act = {delegate.inject{ret, val -> ret * val}} //Å@listlistì‡Ç∑Ç◊ÇƒÇÃóvëfÇ…çÏópÅBèÙÇ›çûÇ›
println "Åy${b}.transpose()Åz" + b.transpose() + "  Åy${b}.transpose().act()Åz" + b.transpose().act()


ArrayList.metaClass.inv = {
	-> def ret = []; delegate.eachWithIndex{it, i -> ret << delegate.indexOf(i + 1) + 1}; ret} // ãtå≥
println "Åy${b}.inv()Åz" + b.inv() + "  Åy${b}.inv().inv()Åz" + b.inv().inv() + "  Åy[].inv()Åz" + [].inv()

ArrayList.metaClass.power = {
	def powerSet=[[]]
	delegate.each{Object obj ->
		powerSet.clone().each{ArrayList tmpA ->
			// ConcurrentModificationExceptionÇîÇØÇÈÇΩÇﬂÅAclone()
			ArrayList tmpAcopy = tmpA.clone()
			tmpAcopy.add(obj)
			powerSet.add(tmpAcopy)
		}
	}
	powerSet
} // ôpèWçá
println "Åy[1,2,3].power()Åz" + [1, 2, 3].power() + "  Åy${Sn(2)}.power()Åz" + Sn(2).power()

Boolean isSubset(ArrayList subset){
	if([0, 1].contains(subset.size())){return false} // ãÛèWçáÅAeÇÕèúÇ≠
	ArrayList twoElement = []
	ArrayList tmpsubset = subset.clone()
	tmpsubset.remove([])
	tmpsubset.each{def element1 ->
		tmpsubset.each{def element2 ->
			twoElement.add([element1.clone(), element2.clone()])
		}
	}
	ArrayList actedElement = []
	twoElement.each {actedElement << (it[0] * (it[1].inv()))} // a*b^(-1)ÇÃåãâ 
	subset.containsAll(actedElement) // actedElementÇÃåãâ Ç™Ç∑Ç◊Çƒä‹Ç‹ÇÍÇƒÇ¢ÇÈÇ©
}
ArrayList.metaClass.subset = {-> delegate.findAll {isSubset(it)}}
ArrayList.metaClass.disp = {-> delegate.eachWithIndex {it, i -> println "Åy${i}Åz${it}"}}

println "Åy[[], [[1, 2]], [[2, 1]], [[1, 2], [2, 1]]].subset()Åz" + [[], [[1, 2]], [[2, 1]], [[1, 2], [2, 1]]].subset()
Sn(3).power().subset().disp()
println Sn(5).size()

