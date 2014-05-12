
def Sn = {def out = []; (1..it as ArrayList).eachPermutation {out << it}; out}
println Sn(3)

List.metaClass.multiply = {delegate[it.collect{it - 1}]} //�@�����Z��`�A�z����g���ē���
def (a,b) = [[1, 3, 2, 4], [4, 1, 2, 3]]
println "�y${a} * ${b}�z" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // �z��ԍ��Ɣz�񐔒l����v���Ă��Ȃ��ӏ����L�^
	ret.size() == 2
} // �݊�����
println "�y${a}.isTranspose()�z" + a.isTranspose() + "  �y${b}.isTranspose()�z" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // �P�ʌ��𐶐�

ArrayList.metaClass.rep = {int tranA, int tranB ->
	use(Collections){delegate.swap(tranA, tranB)}
	delegate
} // ����
println "�y${a}.e().rep(0,1)�z" + a.e().rep(0,1)

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
} // �݊����X�g�쐬

ArrayList.metaClass.act = {delegate.inject{ret, val -> ret * val}} //�@listlist�����ׂĂ̗v�f�ɍ�p�B��ݍ���
println "�y${b}.transpose()�z" + b.transpose() + "  �y${b}.transpose().act()�z" + b.transpose().act()


ArrayList.metaClass.inv = {
	-> def ret = []; delegate.eachWithIndex{it, i -> ret << delegate.indexOf(i + 1) + 1}; ret} // �t��
println "�y${b}.inv()�z" + b.inv() + "  �y${b}.inv().inv()�z" + b.inv().inv() + "  �y[].inv()�z" + [].inv()

ArrayList.metaClass.power = {
	def powerSet=[[]]
	delegate.each{Object obj ->
		powerSet.clone().each{ArrayList tmpA ->
			// ConcurrentModificationException������邽�߁Aclone()
			ArrayList tmpAcopy = tmpA.clone()
			tmpAcopy.add(obj)
			powerSet.add(tmpAcopy)
		}
	}
	powerSet
} // �p�W��
println "�y[1,2,3].power()�z" + [1, 2, 3].power() + "  �y${Sn(2)}.power()�z" + Sn(2).power()

Boolean isSubset(ArrayList subset){
	if([0, 1].contains(subset.size())){return false} // ��W���Ae�͏���
	ArrayList twoElement = []
	ArrayList tmpsubset = subset.clone()
	tmpsubset.remove([])
	tmpsubset.each{def element1 ->
		tmpsubset.each{def element2 ->
			twoElement.add([element1.clone(), element2.clone()])
		}
	}
	ArrayList actedElement = []
	twoElement.each {actedElement << (it[0] * (it[1].inv()))} // a*b^(-1)�̌���
	subset.containsAll(actedElement) // actedElement�̌��ʂ����ׂĊ܂܂�Ă��邩
}
ArrayList.metaClass.subset = {-> delegate.findAll {isSubset(it)}}
ArrayList.metaClass.disp = {-> delegate.eachWithIndex {it, i -> println "�y${i}�z${it}"}}

println "�y[[], [[1, 2]], [[2, 1]], [[1, 2], [2, 1]]].subset()�z" + [[], [[1, 2]], [[2, 1]], [[1, 2], [2, 1]]].subset()
Sn(3).power().subset().disp()
println Sn(5).size()

