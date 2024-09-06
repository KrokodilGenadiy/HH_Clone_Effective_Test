package com.example.hh_clone_effective_test.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hh_clone_effective_test.databinding.FragmentSearchBinding
import com.example.hh_clone_test.search.data.entities.ConfirmButton
import com.example.hh_clone_test.search.data.entities.Vacancy
import com.example.hh_clone_test.search.ui.SearchViewModel
import com.example.hh_clone_test.search.ui.rv_adapters.ButtonAdapterDelegate
import com.example.hh_clone_test.search.ui.rv_adapters.MainCompositeAdapter
import com.example.hh_clone_test.search.ui.rv_adapters.item_decorators.VacancyItemDecorator
import com.example.hh_clone_test.search.ui.rv_adapters.OfferAdapter
import com.example.hh_clone_test.search.ui.rv_adapters.item_decorators.OffersItemDecorator
import com.example.hh_clone_test.search.ui.rv_adapters.VacancyAdapterDelegate
import com.example.hh_clone_test.search.ui.rv_adapters.delegate_adapter.DelegateAdapterItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private val offerAdapter by lazy {
        OfferAdapter()
    }
    private var vacancies_list = emptyList<Vacancy>()
    private val mainAdapter by lazy {
        MainCompositeAdapter.Builder()
            .add(VacancyAdapterDelegate())
            .add(ButtonAdapterDelegate {
                showMoreClick()
            })
            .build()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        setUpVacancyAdapter()
        setUpOfferAdapter()
        lifecycleScope.launch {
            viewModel.apiResponseFlow.collectLatest { response ->
                if (response.vacancies.isNotEmpty()) {
                    vacancies_list = response.vacancies
                    val list: MutableList<DelegateAdapterItem> =
                        vacancies_list.subList(0, 3).toMutableList()
                    list.add(ConfirmButton(response.vacancies.size - 3))
                    mainAdapter.submitList(list)
                }
                if (response.offers.isNotEmpty())
                    offerAdapter.submitList(response.offers)
            }
        }
    }

    private fun setUpVacancyAdapter() {
        binding.vacancyRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VacancyItemDecorator(8))
        }
    }

    private fun setUpOfferAdapter() {
        binding.offerRecycler.apply {
            adapter = offerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(OffersItemDecorator(8))
        }
    }


    private fun showMoreClick() {
        mainAdapter.submitList(vacancies_list)
        with(binding) {
            offerRecycler.visibility = View.GONE
            vacancyForYouTitle.visibility = View.GONE
            sortVacanciesContainer.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}