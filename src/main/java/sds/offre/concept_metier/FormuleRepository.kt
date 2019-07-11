package sds.offre.concept_metier

interface FormuleRepository {

    operator fun get(id: IdFormule): Formule

    fun toutesLesFormules(): Collection<Formule>

    fun addOrReplace(formule: Formule)
}
